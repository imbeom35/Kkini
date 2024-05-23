package com.kkini.core.domain.reaction.repository;

import com.kkini.core.domain.reaction.entity.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {

    public Reaction findByMemberIdAndPostId(Long memberId, Long postId);

}
