package com.kkini.core.domain.recipe.repository;

import com.kkini.core.domain.recipe.dto.request.SearchConditionRequestDto;
import com.kkini.core.domain.recipe.dto.response.RecipeAllListResponseDto;
import com.kkini.core.domain.recipe.dto.response.RecipeDetailResponseDto;
import com.kkini.core.domain.recipe.dto.response.RecipeListMypageResponseDto;
import com.kkini.core.domain.recipe.dto.response.RecipeListResponseDto;
import com.kkini.core.domain.recipe.entity.Recipe;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
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

import static com.kkini.core.domain.category.entity.QCategory.category;
import static com.kkini.core.domain.member.entity.QMember.member;
import static com.kkini.core.domain.recipe.entity.QRecipe.recipe;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RecipeQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 전체 레시피에서 원하는 레시피를 검색하는 로직
     */
    public Page<RecipeListResponseDto> findRecipeList(SearchConditionRequestDto searchCondition, Pageable pageable) {
        List<RecipeListResponseDto> recipeList = jpaQueryFactory
                .select(Projections.constructor(RecipeListResponseDto.class,
                        recipe.id,
                        member.id,
                        member.image,
                        member.nickname,
                        recipe.image,
                        recipe.name,
                        recipe.difficulty
                ))
                .from(recipe)
                .join(member).on(recipe.member.id.eq(member.id))
                .where(
                        categoryEq(searchCondition.getCategoryId()),
                        nameLike(searchCondition.getName()),
                        notDeleted()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(recipeSort(pageable))
                .fetch();

        long count = jpaQueryFactory
                .select(recipe.count())
                .from(recipe)
                .where(
                        categoryEq(searchCondition.getCategoryId()),
                        nameLike(searchCondition.getName()),
                        notDeleted()
                )
                .fetchFirst();

        return new PageImpl<>(recipeList, pageable, count);
    }

    /**
     * 내 레시피를 조회하는 로직
     */
    public Page<RecipeListMypageResponseDto> findMyRecipeList(Long memberId, Pageable pageable) {
        List<RecipeListMypageResponseDto> recipeList = jpaQueryFactory
                .select(Projections.constructor(RecipeListMypageResponseDto.class,
                        recipe.id,
                        recipe.image,
                        recipe.name
                ))
                .from(recipe)
                .join(member).on(recipe.member.id.eq(member.id))
                .where(
                        memberEq(memberId),
                        notDeleted()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(recipeSort(pageable))
                .fetch();

        long count = jpaQueryFactory
                .select(recipe.count())
                .from(recipe)
                .where(
                        memberEq(memberId),
                        notDeleted()
                )
                .fetch().size();

        return new PageImpl<>(recipeList, pageable, count);
    }

    /**
     * 레시피 전체 리스트를 조회하는 로직
     */
    public List<RecipeAllListResponseDto> findAllRecipe() {
        return jpaQueryFactory
                .select(Projections.constructor(RecipeAllListResponseDto.class,
                        recipe.id,
                        recipe.name,
                        recipe.image
                ))
                .from(recipe)
                .where(
                        notDeleted()
                )
                .fetch();
    }

    /**
     * 레시피 상세를 조회하는 로직
     */
    public Optional<RecipeDetailResponseDto> findRecipeDetailById(Long recipeId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(RecipeDetailResponseDto.class,
                        recipe.id,
                        category.id,
                        category.name,
                        member.id,
                        member.image,
                        member.nickname,
                        recipe.name,
                        recipe.time,
                        recipe.difficulty,
                        recipe.ingredient,
                        recipe.image,
                        recipe.steps
                        ))
                .from(recipe)
                .join(member).on(recipe.member.id.eq(member.id))
                .join(category).on(recipe.category.id.eq(category.id))
                .where(recipe.id.eq(recipeId)
                        .and(recipe.deleted.eq(false)))
                .fetchOne());
    }

    /**
     * recipe 관련 정렬 로직
     */
    private OrderSpecifier<?> recipeSort(Pageable page) {
        if (!page.getSort().isEmpty()) {
            for (Sort.Order order : page.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                switch (order.getProperty()){
                    case "id":
                        return new OrderSpecifier<>(direction, recipe.id);
                    case "name":
                        return new OrderSpecifier<>(direction, recipe.name);
                }
            }
        }

        return new OrderSpecifier<>(Order.DESC, recipe.modifyDateTime);
    }

    /**
     * 동적 쿼리를 위한 BooleanExpression
     */
    private BooleanExpression categoryEq(Long categoryId) {
        return categoryId == null ? null : recipe.category.id.eq(categoryId);
    }

    private BooleanExpression nameLike(String name) {
        return StringUtils.isNullOrEmpty(name) ? null : recipe.name.contains(name);
    }

    private BooleanExpression notDeleted() {
        return recipe.deleted.eq(false);
    }

    private BooleanExpression memberEq(Long memberId) {
        return recipe.member.id.eq(memberId);
    }

}
