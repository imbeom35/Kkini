package com.kkini.core.domain.follow.service;

import com.kkini.core.domain.follow.dto.response.FollowListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FollowQueryService {

    // 팔로워 리스트
    Page<FollowListResponseDto> getFollowerList(Long id, Pageable pageable);
    // 팔로우 리스트
    Page<FollowListResponseDto> getFollowList(Long id, Pageable pageable);
    // 팔로워 수
//    int countFollowers(Long id);
    // 팔로우 수
//    int countFollows(Long id);
}
