package com.kkini.core.domain.member.repository;

import com.kkini.core.domain.member.dto.SearchMemberResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;
import static com.kkini.core.domain.member.entity.QMember.member;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MemberQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Page<SearchMemberResponseDto> searchMember(String query, Pageable pageable){
        List<SearchMemberResponseDto> list = jpaQueryFactory
                .select(Projections.constructor(SearchMemberResponseDto.class,
                        member.id,
                        member.name,
                        member.nickname,
                        member.image
                        ))
                .from(member)
                .where(
                        member.nickname.contains(query)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(member.nickname.desc())
                .fetch();

        int count = jpaQueryFactory
                .select(member.count())
                .from(member)
                .fetch().size();

        return new PageImpl<>(list, pageable, count);
    }
}
