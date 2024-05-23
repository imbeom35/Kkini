package com.kkini.core.domain.step.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.kkini.core.domain.step.entity.QStep.step;

@Repository
@Slf4j
@RequiredArgsConstructor
public class StepQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<String> findStepListByRecipeId(Long recipeId) {
        return jpaQueryFactory
                .select(step.content)
                .from(step)
                .where(step.recipe.id.eq(recipeId))
                .fetch();
    }
}
