package com.kkini.core.domain.own.repository;

import com.kkini.core.domain.own.entity.Own;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnRepository extends JpaRepository<Own, Long> {

    Optional<Own> findByMemberIdAndSelected(Long memberId, boolean selected);
}
