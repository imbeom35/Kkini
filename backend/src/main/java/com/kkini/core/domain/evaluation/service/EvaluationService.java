package com.kkini.core.domain.evaluation.service;

import com.kkini.core.domain.evaluation.dto.request.EvaluationRegisterRequestDto;

public interface EvaluationService {

    int saveEvaluation(EvaluationRegisterRequestDto evaluationRegisterRequestDto, Long memberId);

}
