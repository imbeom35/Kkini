package com.kkini.core.domain.follow.service;

import com.kkini.core.domain.follow.dto.request.FollowRequestDto;
import com.kkini.core.domain.oauth2.UserPrincipal;

public interface FollowService {

    // 팔로우 추가 & 팔로워 추가
    void addFollow(FollowRequestDto followRequestDto);

    // 팔로우 삭제
    void deleteFollow(FollowRequestDto followRequestDto);

    // 팔로우 수 조회
    int countFollows(Long id);

    // 팔로워 수 조회
    int countFollowers(Long id);

    int isFollow(FollowRequestDto followRequestDto);
}
