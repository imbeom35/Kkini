package com.kkini.core.domain.evaluation.repository;

import com.kkini.core.domain.evaluation.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    public Evaluation findByMemberIdAndPostId(Long memberId, Long postId);

}
