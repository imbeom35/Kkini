package com.kkini.core.domain.follow.service;

import com.kkini.core.domain.follow.dto.request.FollowRequestDto;
import com.kkini.core.domain.follow.entity.Follow;
import com.kkini.core.domain.follow.repository.FollowRepository;
import com.kkini.core.domain.member.entity.Member;
import com.kkini.core.domain.member.repository.MemberRepository;
import com.kkini.core.domain.oauth2.UserPrincipal;
import com.kkini.core.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FollowServiceImpl implements FollowService{

    private final MemberRepository memberRepository;
    private final FollowRepository followRepository;

    @Override
    public void addFollow(FollowRequestDto followRequestDto) {
        Member me = memberRepository.findById(followRequestDto.getMemberId()).orElseThrow(() -> new NotFoundException(Member.class, followRequestDto.getMemberId()));
        Member target = memberRepository.findById(followRequestDto.getTargetMemberId()).orElseThrow(() -> new NotFoundException(Member.class, followRequestDto.getMemberId()));
        int count = followRepository.countByMe_IdAndTarget_Id(followRequestDto.getMemberId(), followRequestDto.getTargetMemberId());
        if (count == 0){
            followRepository.save(Follow.builder()
                    .me(me)
                    .target(target)
                    .build());
        }
    }

    @Override
    public void deleteFollow(FollowRequestDto followRequestDto) {
        Member me = memberRepository.findById(followRequestDto.getMemberId()).orElseThrow(() -> new NotFoundException(Member.class, followRequestDto.getMemberId()));
        Member target = memberRepository.findById(followRequestDto.getTargetMemberId()).orElseThrow(() -> new NotFoundException(Member.class, followRequestDto.getTargetMemberId()));

        Follow follow = followRepository.findByMe_IdAndTarget_Id(me.getId(), target.getId()).orElseThrow(() -> new NotFoundException(Follow.class, me.getId(), target.getId()));
        followRepository.delete(follow);
    }

    @Override
    public int countFollows(Long id) {
        memberRepository.findById(id).orElseThrow(() -> new NotFoundException(Member.class, id));
        return followRepository.countByMeId(id);
    }

    @Override
    public int countFollowers(Long id) {
        memberRepository.findById(id).orElseThrow(() -> new NotFoundException(Member.class, id));
        return followRepository.countByTargetId(id);
    }

    @Override
    public int isFollow(FollowRequestDto followRequestDto) {
        memberRepository.findById(followRequestDto.getMemberId()).orElseThrow(() -> new NotFoundException(Member.class, followRequestDto.getMemberId()));
        memberRepository.findById(followRequestDto.getTargetMemberId()).orElseThrow(() -> new NotFoundException(Member.class, followRequestDto.getTargetMemberId()));
        int cnt = followRepository.countByMe_IdAndTarget_Id(followRequestDto.getMemberId(), followRequestDto.getTargetMemberId());
        if (cnt == 1) {
            return 1;
        } else{
            return 0;
        }
    }


}
