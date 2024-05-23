package com.kkini.core.domain.history.repository;

import com.kkini.core.domain.history.dto.response.HistoryResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Projection;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.kkini.core.domain.history.entity.QHistory.history;

@Repository
@Slf4j
@RequiredArgsConstructor
public class HistoryQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<HistoryResponseDto> findMyHistoryList(Long memberId) {
        return jpaQueryFactory
                .select(Projections.constructor(HistoryResponseDto.class,
                        history.id,
                        history.word,
                        history.createDateTime
                ))
                .from(history)
                .where(history.member.id.eq(memberId))
                .fetch();
    }

}
