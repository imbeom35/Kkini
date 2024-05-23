package com.kkini.core.domain.post.repository;

import com.kkini.core.domain.post.dto.response.PostListResponseDto;
import com.kkini.core.domain.post.entity.Post;
import com.kkini.core.global.exception.NotFoundException;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.kkini.core.domain.comment.entity.QComment.comment;
import static com.kkini.core.domain.evaluation.entity.QEvaluation.evaluation;
import static com.kkini.core.domain.follow.entity.QFollow.follow;
import static com.kkini.core.domain.member.entity.QMember.member;
import static com.kkini.core.domain.post.entity.QPost.post;
import static com.kkini.core.domain.postimage.entity.QPostImage.postImage;
import static com.kkini.core.domain.reaction.entity.QReaction.reaction;
import static com.kkini.core.domain.recipe.entity.QRecipe.recipe;
import static com.kkini.core.domain.scrap.entity.QScrap.scrap;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PostQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 타임라인 조회
     */
    public Page<PostListResponseDto> findPostByTimeline(Pageable pageable, Long memberId) {
        List<Long> followList = jpaQueryFactory
                .select(follow.target.id)
                .from(follow)
                .where(follow.me.id.eq(memberId))
                .fetch();

        List<PostListResponseDto> postList = selectPostListResponseDtoFrom(memberId)
                .where(feedCondition(memberId, followList))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(new OrderSpecifier<>(Order.DESC, post.id))
                .fetch();

        setPostInfoList(postList);

        long count = jpaQueryFactory
                .select(post.count())
                .from(post)
                .where(feedCondition(memberId, followList))
                .fetchFirst();

        return new PageImpl<>(postList, pageable, count);
    }

    /**
     * 마이페이지 조회
     */
    public Page<PostListResponseDto> findPostByMypage(Pageable pageable, Long memberId) {

        List<PostListResponseDto> postList = selectPostListResponseDtoFrom(memberId)
                .where(memberEq(memberId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(new OrderSpecifier<>(Order.DESC, post.id))
                .fetch();

        setPostInfoList(postList);

        long count = jpaQueryFactory
                .select(post.count())
                .from(post)
                .where(memberEq(memberId))
                .fetchFirst();

        return new PageImpl<>(postList, pageable, count);
    }

    /**
     * 검색 조회
     */
    public Page<PostListResponseDto> findPostBySearch(Pageable pageable, Long memberId, String search) {

        List<PostListResponseDto> postList = selectPostListResponseDtoFrom(memberId)
                .where(post.contents.contains(search))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(new OrderSpecifier<>(Order.DESC, post.id))
                .fetch();

        setPostInfoList(postList);

        long count = jpaQueryFactory
                .select(post.count())
                .from(post)
                .where(post.contents.contains(search))
                .fetchFirst();

        return new PageImpl<>(postList, pageable, count);
    }

    /**
     * 상세 조회
     */
    public PostListResponseDto findPostDetail(Long postId, Long memberId) {

        PostListResponseDto findPost = selectPostListResponseDtoFrom(memberId)
                .where(post.id.eq(postId))
                .fetchOne();

        if(findPost == null) {
            throw new NotFoundException(Post.class, postId);
        }
        setPostInfo(findPost);

        return findPost;
    }

    /**
     * 끼니 추천 알고리즘 조회
     */
    public List<PostListResponseDto> findPostByAlgorithm(Pageable pageable, Long categoryId, Long memberId) {

        List<PostListResponseDto> postList = selectPostListResponseDtoFrom(memberId)
                .where(categoryEq(categoryId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        setPostInfoList(postList);

        return postList;
    }

    /**
     * 나머지 포스트 조회
     */
    public List<PostListResponseDto> findRemainPost(long size, Long memberId) {

        List<PostListResponseDto> postList = selectPostListResponseDtoFrom(memberId)
                .orderBy(post.modifyDateTime.asc())
                .limit(size)
                .fetch();

        setPostInfoList(postList);

        return postList;
    }

    private BooleanBuilder feedCondition(Long memberId, List<Long> followList) {
        return new BooleanBuilder()
                .or(memberEq(memberId))
                .or(existFollowing(followList));
    }

    private BooleanExpression memberEq(Long memberId) {
        return post.member.id.eq(memberId);
    }

    private BooleanExpression existFollowing(List<Long> followList) {
        return post.member.id.in(followList);
    }

    private BooleanExpression categoryEq(Long categoryId) {
        return categoryId==0L ? null : post.recipe.category.id.eq(categoryId);
    }

    private JPAQuery<PostListResponseDto> selectPostListResponseDtoFrom(Long memberId) {
        return jpaQueryFactory
                .select(Projections.constructor(PostListResponseDto.class,
                        post.id,
                        post.contents,
                        post.createDateTime,
                        post.likeCnt,
                        post.disLikeCnt,
                        post.avgPrice,
                        evaluation.price,
                        member.id.eq(memberId).as("isMine"),
                        member.id,
                        member.nickname,
                        member.image,
                        recipe.id,
                        recipe.name,
                        reaction.state,
                        scrap.id.isNotNull()
                ))
                .from(post)
                .leftJoin(recipe).on(post.recipe.id.eq(recipe.id))
                .leftJoin(member).on(post.member.id.eq(member.id))
                .leftJoin(reaction).on(post.id.eq(reaction.post.id).and(reaction.member.id.eq(memberId)))
                .leftJoin(scrap).on(post.id.eq(scrap.post.id).and(scrap.member.id.eq(memberId)))
                .leftJoin(evaluation).on(post.id.eq(evaluation.post.id).and(evaluation.member.id.eq(memberId)));
    }

    private void setPostInfoList(List<PostListResponseDto> postList) {
        for (PostListResponseDto postListResponseDto : postList) {
            setPostInfo(postListResponseDto);
        }
    }

    private void setPostInfo(PostListResponseDto postListResponseDto) {
        List<String> imageList = jpaQueryFactory
                .select(postImage.image)
                .from(postImage)
                .where(postImage.post.id.eq(postListResponseDto.getId()))
                .fetch();

        List<Long> imageIndexList = jpaQueryFactory
                .select(postImage.id)
                .from(postImage)
                .where(postImage.post.id.eq(postListResponseDto.getId()))
                .fetch();

        Long commentCnt = jpaQueryFactory
                .select(comment.count())
                .from(comment)
                .where(comment.post.id.eq(postListResponseDto.getId()))
                .fetchFirst();

        postListResponseDto.setImageList(imageList);
        postListResponseDto.setImageIndexList(imageIndexList);
        postListResponseDto.setCommentCnt(commentCnt.intValue());
    }

}
