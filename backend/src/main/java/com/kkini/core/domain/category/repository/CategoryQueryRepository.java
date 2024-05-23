package com.kkini.core.domain.category.repository;

import com.kkini.core.domain.category.dto.response.CategoryListResponseDto;
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

import static com.kkini.core.domain.category.entity.QCategory.category;
import static com.kkini.core.domain.member.entity.QMember.member;
import static com.kkini.core.domain.recipe.entity.QRecipe.recipe;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CategoryQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<CategoryListResponseDto> getAllCategories() {

        return jpaQueryFactory
                .select(Projections.constructor(CategoryListResponseDto.class,
                        category.id,
                        category.name
                ))
                .from(category)
                .fetch();
    }

}
