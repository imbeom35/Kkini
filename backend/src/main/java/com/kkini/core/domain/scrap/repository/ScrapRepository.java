package com.kkini.core.domain.scrap.repository;

import com.kkini.core.domain.scrap.entity.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {

    int countByMember_Id(Long id);

    Optional<Scrap> findByMember_IdAndPost_Id(Long memberId, Long postId);
    int countByMember_IdAndPost_Id(Long memberId, Long postId);
//    int findByMember_IdAndId(Long memberId, Long postId);
}
