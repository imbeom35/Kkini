package com.kkini.core.domain.badge.repository;

import com.kkini.core.domain.badge.dto.response.BadgeListResponseDto;
import com.kkini.core.domain.recipe.dto.request.SearchConditionRequestDto;
import com.kkini.core.domain.recipe.dto.response.RecipeDetailResponseDto;
import com.kkini.core.domain.recipe.dto.response.RecipeListResponseDto;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.kkini.core.domain.badge.entity.QBadge.badge;
import static com.kkini.core.domain.category.entity.QCategory.category;
import static com.kkini.core.domain.member.entity.QMember.member;
import static com.kkini.core.domain.own.entity.QOwn.own;
import static com.kkini.core.domain.recipe.entity.QRecipe.recipe;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BadgeQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<BadgeListResponseDto> findMyBadgeList(Long memberId) {
        List<BadgeListResponseDto> recipeList = jpaQueryFactory
                .select(Projections.constructor(BadgeListResponseDto.class,
                        badge.id,
                        badge.name,
                        badge.image,
                        own.createDateTime
                ))
                .from(badge)
                .join(own).on(own.badge.id.eq(badge.id))
                .where(own.member.id.eq(memberId))
                .fetch();

        return recipeList;
    }

}
