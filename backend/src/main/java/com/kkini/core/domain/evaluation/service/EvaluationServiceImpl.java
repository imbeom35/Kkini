package com.kkini.core.domain.evaluation.service;

import com.kkini.core.domain.evaluation.dto.request.EvaluationRegisterRequestDto;
import com.kkini.core.domain.evaluation.entity.Evaluation;
import com.kkini.core.domain.evaluation.repository.EvaluationRepository;
import com.kkini.core.domain.member.entity.Member;
import com.kkini.core.domain.member.repository.MemberRepository;
import com.kkini.core.domain.post.entity.Post;
import com.kkini.core.domain.post.repository.PostRepository;
import com.kkini.core.domain.preference.entity.Preference;
import com.kkini.core.domain.preference.repository.PreferenceRepository;
import com.kkini.core.domain.recipe.entity.Recipe;
import com.kkini.core.domain.recipe.repository.RecipeRepository;
import com.kkini.core.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final PreferenceRepository preferenceRepository;
    private final RecipeRepository recipeRepository;

    @Override
    public int saveEvaluation(EvaluationRegisterRequestDto dto, Long memberId) {
        int oldPrice = 0;
        boolean isNew = false;

        Member writer = memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException(Evaluation.class, memberId));
        Post post = postRepository.findById(dto.getPostId()).orElseThrow(() -> new NotFoundException(Post.class, dto.getPostId()));
        Evaluation evaluation = evaluationRepository.findByMemberIdAndPostId(memberId, dto.getPostId());
        Recipe recipe = null;

        if(post.getRecipe() != null) {
            recipe = recipeRepository.findById(post.getRecipe().getId()).orElseThrow(() -> new NotFoundException(Recipe.class, post.getRecipe().getId()));
        }

        // 신규
        if(evaluation == null) {
            evaluationRepository.save(
                    Evaluation.builder()
                            .member(writer)
                            .post(post)
                            .price(dto.getPrice())
                            .build()
            );

            isNew = true;
        }
        
        // 수정
        else {
            oldPrice = evaluation.getPrice();

            evaluation.setPrice(dto.getPrice());
            evaluationRepository.save(evaluation);
        }

        // 평균 반영
        post.changePrice(oldPrice, dto.getPrice(), isNew);
        postRepository.save(post);

        // 가중치 갱신
        if(recipe != null && isNew) {
            Preference preference = preferenceRepository.findByCategoryIdAndMemberId(recipe.getCategory().getId(), memberId);
            preference.increaseWeightByEval();
            preferenceRepository.save(preference);
        }

        return post.getAvgPrice();
    }

}
