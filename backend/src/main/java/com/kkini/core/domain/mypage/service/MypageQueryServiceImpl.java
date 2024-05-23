package com.kkini.core.domain.mypage.service;


import com.kkini.core.domain.follow.repository.FollowRepository;
import com.kkini.core.domain.member.entity.Member;
import com.kkini.core.domain.member.repository.MemberRepository;
import com.kkini.core.domain.mypage.dto.response.MypageInfoResponseListDto;
import com.kkini.core.domain.mypage.repository.MypageQueryRepository;
import com.kkini.core.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class MypageQueryServiceImpl implements MypageQueryService{

    private final MemberRepository memberRepository;
    private final MypageQueryRepository mypageQueryRepository;


    @Override
    public MypageInfoResponseListDto getMypageInfo(Long memberId) {
        memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException(Member.class, memberId));
        return mypageQueryRepository.getMyPageInfo(memberId);
    }

    @Override
    public String getProfileImage(Long memberId) {
        memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException(Member.class, memberId));
        return mypageQueryRepository.getProfileImage(memberId);
    }
}
