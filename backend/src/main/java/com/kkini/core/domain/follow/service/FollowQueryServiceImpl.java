package com.kkini.core.domain.follow.service;

import com.kkini.core.domain.follow.dto.response.FollowListResponseDto;
import com.kkini.core.domain.follow.repository.FollowQueryRepository;
import com.kkini.core.domain.member.entity.Member;
import com.kkini.core.domain.member.repository.MemberRepository;
import com.kkini.core.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor

public class FollowQueryServiceImpl implements FollowQueryService{

    private final FollowQueryRepository followQueryRepository;
    private final MemberRepository memberRepository;

    @Override
    public Page<FollowListResponseDto> getFollowerList(Long id, Pageable pageable) {
        memberRepository.findById(id).orElseThrow(() -> new NotFoundException(Member.class, id));
        return followQueryRepository.getFollowerList(id, pageable);
    }

    @Override
    public Page<FollowListResponseDto> getFollowList(Long id, Pageable pageable) {
        memberRepository.findById(id).orElseThrow(() -> new NotFoundException(Member.class, id));
        return followQueryRepository.getFollowList(id, pageable);
    }

//    @Override
//    public int countFollowers(Long id) {
//        return followQueryRepository.countFollowers(id);
//    }
//
//    @Override
//    public int countFollows(Long id) {
//        return followQueryRepository.countFollows(id);
//    }
}
