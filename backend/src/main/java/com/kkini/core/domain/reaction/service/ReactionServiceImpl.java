package com.kkini.core.domain.reaction.service;

import com.kkini.core.domain.member.entity.Member;
import com.kkini.core.domain.member.repository.MemberRepository;
import com.kkini.core.domain.post.entity.Post;
import com.kkini.core.domain.post.repository.PostRepository;
import com.kkini.core.domain.preference.entity.Preference;
import com.kkini.core.domain.preference.repository.PreferenceRepository;
import com.kkini.core.domain.reaction.dto.request.ReactionRegisterRequestDto;
import com.kkini.core.domain.reaction.entity.Reaction;
import com.kkini.core.domain.reaction.repository.ReactionRepository;
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
public class ReactionServiceImpl implements ReactionService {

    private final Boolean LIKE = true;
    private final Boolean DISLIKE = false;

    private final ReactionRepository reactionRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final PreferenceRepository preferenceRepository;
    private final RecipeRepository recipeRepository;

    @Override
    public Boolean saveReaction(ReactionRegisterRequestDto dto, Long memberId) {
        Boolean oldState = null;

        Member writer = memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException(Reaction.class, memberId));
        Post post = postRepository.findById(dto.getPostId()).orElseThrow(() -> new NotFoundException(Post.class, dto.getPostId()));
        Reaction reaction = reactionRepository.findByMemberIdAndPostId(memberId, dto.getPostId());

        // 신규
        if(reaction == null) {
            reaction = Reaction.builder()
                    .member(writer)
                    .post(post)
                    .state(dto.getState())
                    .build();

            reactionRepository.save(reaction);
        }

        // 수정
        else {
            oldState = reaction.getState();

            reactionRepository.save(setReaction(reaction, oldState, dto.getState()));
        }

        // Count 갱신
        postRepository.save(setPostReactionCount(post, oldState, dto.getState()));

        // 가중치 갱신
        if(post.getRecipe() != null) {
            Recipe recipe = recipeRepository.findById(post.getRecipe().getId()).orElseThrow(() -> new NotFoundException(Recipe.class, post.getRecipe().getId()));

            if(recipe != null) {
                Preference preference = preferenceRepository.findByCategoryIdAndMemberId(recipe.getCategory().getId(), memberId);
                preferenceRepository.save(setPreference(preference, oldState, dto.getState()));
            }
        }

        return reaction.getState();
    }

    // Reaction 갱신
    private Reaction setReaction(Reaction reaction, Boolean oldState, Boolean newState) {
        if(oldState == null && newState == LIKE) {
            reaction.setState(LIKE);
        }

        else if(oldState == null && newState == DISLIKE) {
            reaction.setState(DISLIKE);
        }

        else if(oldState == LIKE && newState == LIKE) {
            reaction.setState(null);
        }

        else if(oldState == LIKE && newState == DISLIKE) {
            reaction.setState(DISLIKE);
        }

        else if(oldState == DISLIKE && newState == LIKE) {
            reaction.setState(LIKE);
        }

        else if(oldState == DISLIKE && newState == DISLIKE) {
            reaction.setState(null);
        }

        return reaction;
    }

    // Post 갱신
    private Post setPostReactionCount(Post post, Boolean oldState, Boolean newState) {
        if(oldState == null && newState == LIKE) {
            post.increaseLikeCnt();
        }

        else if(oldState == null && newState == DISLIKE) {
            post.increaseDisLikeCnt();
        }

        else if(oldState == LIKE && newState == LIKE) {
            post.decreaseLikeCnt();
        }

        else if(oldState == LIKE && newState == DISLIKE) {
            post.decreaseLikeCnt();
            post.increaseDisLikeCnt();
        }

        else if(oldState == DISLIKE && newState == LIKE) {
            post.decreaseDisLikeCnt();
            post.increaseLikeCnt();
        }

        else if(oldState == DISLIKE && newState == DISLIKE) {
            post.decreaseDisLikeCnt();
        }

        return post;
    }

    // Preference 갱신
    private Preference setPreference(Preference preference, Boolean oldState, Boolean newState) {
        if(oldState == null && newState == LIKE) {
            preference.increaseWeightByLike();
        }

        else if(oldState == null && newState == DISLIKE) {
            preference.decreaseWeightByLike();
        }

        else if(oldState == LIKE && newState == LIKE) {
            preference.decreaseWeightByLike();
        }

        else if(oldState == LIKE && newState == DISLIKE) {
            preference.decreaseWeightByLike();
            preference.decreaseWeightByLike();
        }

        else if(oldState == DISLIKE && newState == LIKE) {
            preference.increaseWeightByLike();
            preference.increaseWeightByLike();
        }

        else if(oldState == DISLIKE && newState == DISLIKE) {
            preference.increaseWeightByLike();
        }

        return preference;
    }

}
