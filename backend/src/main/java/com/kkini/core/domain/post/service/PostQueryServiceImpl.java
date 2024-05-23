package com.kkini.core.domain.post.service;

import com.kkini.core.domain.post.dto.response.PostListResponseDto;
import com.kkini.core.domain.post.repository.PostQueryRepository;
import com.kkini.core.domain.post.repository.PostRepository;
import com.kkini.core.domain.preference.repository.PreferenceQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class PostQueryServiceImpl implements PostQueryService {

    private final PostQueryRepository postQueryRepository;
    private final PreferenceQueryRepository preferenceQueryRepository;
    private final PostRepository postRepository;

    @Value("${page-size.post.algorithm}")
    private int PAGE_SIZE;
    private final int[] feedCntArr = {7, 5, 4, 3, 1};

    // 타임라인 조회
    @Override
    public Page<PostListResponseDto> getPostList(Pageable pageable, Long memberId) {
        return postQueryRepository.findPostByTimeline(pageable, memberId);
    }

    // 상세 조회
    @Override
    public PostListResponseDto getPostDetail(Long postId, Long memberId) {
        return postQueryRepository.findPostDetail(postId, memberId);
    }

    // 내 포스트 조회
    @Override
    public Page<PostListResponseDto> getMyPagePostList(Pageable pageable, Long memberId) {
        return postQueryRepository.findPostByMypage(pageable, memberId);
    }

    // 포스트 검색
    @Override
    public Page<PostListResponseDto> getSearchPostList(Pageable pageable, Long memberId, String search) {
        return postQueryRepository.findPostBySearch(pageable, memberId, search);
    }
    
    // 알고리즘 조회
    @Override
    public Page<PostListResponseDto> getAlgorithmPostList(int page, Long memberId) {
        List<PostListResponseDto> postList = new ArrayList<>();
        List<Long> interestingCategoryIdList = preferenceQueryRepository.getInterestingCategoryIdList(memberId);

        for (int i=0; i<5; i++) {
            Pageable pageable = PageRequest.of(page, feedCntArr[i], Sort.by("id").descending());
            Long categoryId = interestingCategoryIdList.get(i);
            List<PostListResponseDto> content = postQueryRepository.findPostByAlgorithm(pageable, categoryId, memberId);
            postList.addAll(content);
        }

        // 페이지 계산을 위한 임시 Page: 수식으로 바꿀 수 있음
        PageImpl<PostListResponseDto> tempPage = new PageImpl<>(postList, PageRequest.of(page, PAGE_SIZE), postRepository.count());

        // 20개를 못가져왔으면
        if(postList.size()<PAGE_SIZE) {
            // 남은 개수만큼 최근껄로 가져옴
            if(tempPage.isLast()) {     // 마지막 페이지면
                if (postList.size() < tempPage.getTotalElements() % PAGE_SIZE) {        // 사이즈는 맞춰서 리턴하자
                    postList.addAll(postQueryRepository.findRemainPost(tempPage.getTotalElements()%PAGE_SIZE - postList.size(), memberId));
                }
            } else {     // 마지막 페이지가 아닌데 카테고리에 맞는 포스트가 부족해서 못가져온거면
                postList.addAll(postQueryRepository.findRemainPost(PAGE_SIZE - postList.size(), memberId));
            }
        }

        return new PageImpl<>(postList, PageRequest.of(page, PAGE_SIZE), postRepository.count());
    }

}
