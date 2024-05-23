package com.kkini.core.domain.badge.service;

import com.kkini.core.domain.badge.dto.response.BadgeListResponseDto;

import java.util.List;

public interface BadgeQueryService {
    List<BadgeListResponseDto> getMyBadgeList(Long memberId);
}
