package com.kkini.core.domain.collection.repository;

import com.kkini.core.domain.collection.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {

    Optional<Collection> findByMemberIdAndRecipeId(Long memberId, Long recipeId);

}
