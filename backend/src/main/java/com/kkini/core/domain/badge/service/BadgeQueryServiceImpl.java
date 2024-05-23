package com.kkini.core.domain.badge.service;

import com.kkini.core.domain.badge.dto.response.BadgeListResponseDto;
import com.kkini.core.domain.badge.repository.BadgeQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class BadgeQueryServiceImpl implements BadgeQueryService {

    private final BadgeQueryRepository badgeQueryRepository;

    @Override
    public List<BadgeListResponseDto> getMyBadgeList(Long memberId) {
        return badgeQueryRepository.findMyBadgeList(memberId);
    }

}
