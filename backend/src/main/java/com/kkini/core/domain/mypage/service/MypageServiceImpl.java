package com.kkini.core.domain.mypage.service;

import com.kkini.core.domain.member.entity.Member;
import com.kkini.core.domain.member.repository.MemberRepository;
import com.kkini.core.domain.mypage.repository.MypageRepository;
import com.kkini.core.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MypageServiceImpl implements MypageService {

    private final MemberRepository memberRepository;

    @Override
    public void withDrawalMembership(Long memberId) {
        log.debug("회원 탈퇴를 진행할 회원 : {}", memberId);
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException(Member.class, memberId));
        log.debug("{}", member.toString());
        memberRepository.delete(member);
    }
}
