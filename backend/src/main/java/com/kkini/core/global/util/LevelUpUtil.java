package com.kkini.core.global.util;

import com.kkini.core.domain.badge.entity.Badge;
import com.kkini.core.domain.badge.repository.BadgeRepository;
import com.kkini.core.domain.member.entity.Member;
import com.kkini.core.domain.member.repository.MemberRepository;
import com.kkini.core.domain.own.entity.Own;
import com.kkini.core.domain.own.repository.OwnRepository;
import com.kkini.core.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LevelUpUtil {

    private final MemberRepository memberRepository;
    private final OwnRepository ownRepository;
    private final BadgeRepository badgeRepository;

    /**
     * 별을 획득한 이후 호출하는 메서드
     * 멤버의 stars가 레벨업 할만큼 모였으면 레벨업시키고 뱃지 획득까지 처리한다.
     * */
    public void checkLevelUp(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException(Member.class, memberId));
        int starsToLevelUp = member.getLevel() * 5;
        if (member.getStars() >= starsToLevelUp) {
            member.loseStars(starsToLevelUp);
            member.levelUp();
            int level = member.getLevel();
            if (level < 10 && level % 5 == 0) {
                Own curOwn = ownRepository.findByMemberIdAndSelected(memberId, true).orElseThrow(() -> new NotFoundException(Own.class, memberId));
                curOwn.changeSelected();
                Badge badge = badgeRepository.findById(curOwn.getBadge().getId() + 1).orElseThrow(() -> new NotFoundException(Badge.class, memberId));
                ownRepository.save(Own.builder()
                        .member(member)
                        .badge(badge)
                        .selected(true)
                        .build()
                );
            }
        }
    }

}
