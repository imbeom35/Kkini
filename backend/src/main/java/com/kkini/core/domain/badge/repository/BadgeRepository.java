package com.kkini.core.domain.badge.repository;

import com.kkini.core.domain.badge.entity.Badge;
import com.kkini.core.domain.own.entity.Own;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {

}
