package com.kkini.core.domain.follow.repository;

import com.kkini.core.domain.follow.entity.Follow;
import com.kkini.core.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    int countByMeId(long id);
    int countByTargetId(long id);
    int countByMe_IdAndTarget_Id(Long memberId, Long targetMemberId);
    Optional<Follow> findByMe_IdAndTarget_Id(Long memberId, Long targetId);

}
