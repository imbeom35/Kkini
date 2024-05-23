package com.kkini.core.domain.mypage.repository;

import com.kkini.core.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MypageRepository extends JpaRepository<Member, Long> {

    @Query("SELECT image FROM Member where id = :memberId")
    String findImageById(@Param("memberId") Long memberId);
}
