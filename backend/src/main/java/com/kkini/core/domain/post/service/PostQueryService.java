package com.kkini.core.domain.post.service;

import com.kkini.core.domain.post.dto.response.PostListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostQueryService {

    Page<PostListResponseDto> getPostList(Pageable pageable, Long memberId);

    PostListResponseDto getPostDetail(Long postId, Long memberId);

    Page<PostListResponseDto> getMyPagePostList(Pageable pageable, Long memberId);

    Page<PostListResponseDto> getSearchPostList(Pageable pageable, Long memberId, String search);

    Page<PostListResponseDto> getAlgorithmPostList(int page, Long memberId);

}
