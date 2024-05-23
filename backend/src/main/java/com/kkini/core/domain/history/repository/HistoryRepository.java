package com.kkini.core.domain.history.repository;

import com.kkini.core.domain.history.entity.History;
import com.kkini.core.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    @Modifying
    @Query("delete from History h where h.member = :member")
    void deleteAllByMember(Member member);

}
