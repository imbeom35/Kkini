package com.kkini.core.domain.mypage.repository;


import com.kkini.core.domain.mypage.dto.response.MypageInfoResponseListDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import static com.kkini.core.domain.member.entity.QMember.member;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MypageQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;


    /**
     * 
     * 마이페이지 정보 조회
     * @param memberId (정보를 조회할 멤버 식별자)
     * @return 마이페이지 정보 리스트
     */
    public MypageInfoResponseListDto getMyPageInfo(Long memberId){

        return jpaQueryFactory
                .select(Projections.constructor(MypageInfoResponseListDto.class,
                        member.name,
                        member.nickname,
                        member.level,
                        member.stars,
                        member.image,
                        member.email,
                        member.authProvider
                        ))
                .from(member)
                .where(
                        member.id.eq(memberId)
                ).fetchFirst();
    }

    /**
     * 프로필 이미지 조회
     * @param memberId (조회할 멤버 식별자)
     * @return 프로필 이미지 링크
     */
    public String getProfileImage(Long memberId){

        return jpaQueryFactory
                .select(member.image)
                .from(member)
                .where(
                        member.id.eq(memberId)
                )
                .fetchFirst();
    }

}
