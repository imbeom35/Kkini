package com.kkini.core.domain.comment.repository;

import com.kkini.core.domain.comment.dto.response.CommentListResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.kkini.core.domain.comment.entity.QComment.comment;
import static com.kkini.core.domain.member.entity.QMember.member;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CommentQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<CommentListResponseDto> findCommentList(Long postId) {
        List<CommentListResponseDto> commentList = jpaQueryFactory
                .select(Projections.constructor(CommentListResponseDto.class,
                    comment.id,
                    comment.parents.id,
                    member.id,
                    member.nickname,
                    member.image,
                    comment.createDateTime,
                    comment.contents
                ))
                .from(comment)
                .leftJoin(member).on(comment.member.id.eq(member.id))
                .where(comment.post.id.eq(postId))
                .orderBy(comment.createDateTime.asc())
                .fetch();

        return commentList;
    }

}
