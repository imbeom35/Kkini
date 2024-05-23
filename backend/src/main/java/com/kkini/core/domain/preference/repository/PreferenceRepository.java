package com.kkini.core.domain.preference.repository;

import com.kkini.core.domain.preference.entity.Preference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenceRepository extends JpaRepository<Preference, Long> {

    Preference findByCategoryIdAndMemberId(Long categoryId, Long memberId);

    Long countByMember_Id(Long memberId);
}
