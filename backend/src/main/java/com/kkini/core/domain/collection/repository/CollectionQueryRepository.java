package com.kkini.core.domain.collection.repository;

import com.kkini.core.domain.collection.dto.response.CollectionListResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static com.kkini.core.domain.collection.entity.QCollection.collection;
import static com.kkini.core.domain.recipe.entity.QRecipe.recipe;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CollectionQueryRepository {

    private final int LIST_SIZE = 1;
    private final JPAQueryFactory jpaQueryFactory;

    public List<CollectionListResponseDto> findRandomCollection(Long memberId) {

//        List<Long> myCollectionList = jpaQueryFactory
//                .select(collection.id)
//                .from(collection)
//                .where(
//                        memberEq(memberId)
//                )
//                .fetch();

        return jpaQueryFactory
                .select(Projections.constructor(CollectionListResponseDto.class,
                        recipe.id,
                        recipe.image
                ))
                .from(collection)
                .join(recipe).on(collection.recipe.id.eq(recipe.id))
                .where(collection.member.id.eq(memberId))
                .fetch();
    }

//    public List<CollectionListResponseDto> findRandomCollectionList(Long memberId, Integer difficulty) {
//
//        List<Long> myCollectionList = jpaQueryFactory
//                .select(collection.id)
//                .from(collection)
//                .where(
//                        memberEq(memberId),
//                        difficultyEq(difficulty)
//                )
//                .fetch();
//
//        List<Long> RandomList = getRandom(myCollectionList, LIST_SIZE);
//
//        return jpaQueryFactory
//                .select(Projections.constructor(CollectionListResponseDto.class,
//                        recipe.id,
//                        recipe.image
//                ))
//                .from(collection)
//                .join(collection.recipe, recipe)
//                .where(collection.id.in(RandomList))
//                .fetch();
//    }

    private BooleanExpression difficultyEq(Integer difficulty) {
        return difficulty != null ? recipe.difficulty.eq(difficulty) : null;
    }

    private BooleanExpression memberEq(Long memberId) {
        return memberId != null ? collection.member.id.eq(memberId) : null;
    }

    private Long getRandom(List<Long> srcList) {

        if (srcList.size() == 0) {
            return 0L;
        }

        int min = 0;
        int max = srcList.size();

        return ThreadLocalRandom.current().nextLong(min, max + 1);
    }
}
