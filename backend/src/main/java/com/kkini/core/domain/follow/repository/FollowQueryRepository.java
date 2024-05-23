package com.kkini.core.domain.follow.repository;

import com.kkini.core.domain.follow.dto.response.FollowListResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.kkini.core.domain.follow.entity.QFollow.follow;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FollowQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 팔로워 리스트 조회
     * @param id (조회를 원하는 회원의 식별자)
     * @return 팔로워 리스트
     */
    public Page<FollowListResponseDto> getFollowerList(long id, Pageable pageable){

        List<FollowListResponseDto> list = jpaQueryFactory
                .select(Projections.constructor(FollowListResponseDto.class,
                        follow.id,
                        follow.me.id,
                        follow.me.nickname,
                        follow.me.image
                        ))
                .from(follow)
                .where(
                        follow.target.id.eq(id)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(follow.id.desc())
                .fetch();

        int count = jpaQueryFactory
                .select(follow.count())
                .from(follow)
                .fetch().size();

        return new PageImpl<>(list, pageable, count);
        
    }

    /**
     * 팔로우 리스트 조회
     * @param id (조회를 원하는 멤버 식별자)
     * @return 팔로우 리스트
     */
    public Page<FollowListResponseDto> getFollowList(long id, Pageable pageable){

         List<FollowListResponseDto> list = jpaQueryFactory
                .select(Projections.constructor(FollowListResponseDto.class,
                        follow.id,
                        follow.target.id,
                        follow.target.nickname,
                        follow.target.image
                ))
                .from(follow)
                .where(
                        follow.me.id.eq(id)
                )
                 .offset(pageable.getOffset())
                 .limit(pageable.getPageSize())
                 .orderBy(follow.id.desc())
                .fetch();

         int count = jpaQueryFactory
                 .select(follow.count())
                 .from(follow)
                 .fetch().size();

        return new PageImpl<>(list, pageable, count);
    }

    /**
     * 회원의 팔로워 수 조회
     * @param id (조회를 원하는 멤버 식별자)
     * @return 팔로워 수
     */
//    public int countFollowers(long id){
//        return jpaQueryFactory
//                .select(follow.count())
//                .from(follow)
//                .where(
//                        follow.target.id.eq(id)
//                )
//                .fetch().size();
//    }

    /**
     * 회원의 팔로우 수 조회
     * @param id (조회를 원하는 멤버 식별자)
     * @return 팔로우 수
     */
//    public int countFollows(long id){
//        return jpaQueryFactory
//                .select(follow.count())
//                .from(follow)
//                .where(
//                        follow.me.id.eq(id)
//                )
//                .fetch().size();
//    }
}
