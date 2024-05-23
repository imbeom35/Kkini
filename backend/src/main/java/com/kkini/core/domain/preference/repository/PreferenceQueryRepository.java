package com.kkini.core.domain.preference.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.kkini.core.domain.preference.entity.QPreference.preference;

@Repository
@RequiredArgsConstructor
public class PreferenceQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 카테고리 ID를 선호도가 높은 순서대로 정렬하여 배열로 리턴
     * 이 순서를 이용하여 알맞은 개수의 포스트 조회
     */
    public List<Long> getInterestingCategoryIdList(Long memberId) {
        return jpaQueryFactory.select(
                        preference.category.id
                )
                .from(preference)
                .where(preference.member.id.eq(memberId))
                .orderBy(preference.weight.desc())
                .fetch();
    }
}
