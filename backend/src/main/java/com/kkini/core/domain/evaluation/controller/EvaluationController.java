package com.kkini.core.domain.evaluation.controller;

import com.kkini.core.domain.evaluation.dto.request.EvaluationRegisterRequestDto;
import com.kkini.core.domain.evaluation.service.EvaluationService;
import com.kkini.core.domain.oauth2.UserPrincipal;
import com.kkini.core.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.kkini.core.global.response.Response.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/evaluation")
@Slf4j
@Tag(name = "Evaluation", description = "평가 관리 API")
public class EvaluationController {

    private final EvaluationService evaluationService;

    @Operation(summary = "포스트 음식 평가", description = "포스트 음식의 가치를 금액으로 평가한다.")
    @Parameters({
            @Parameter(name = "id", description = "포스트 식별자"),
            @Parameter(name = "price", description = "금액")
    })
    @PostMapping
    public Response<Integer> setEvaluation(
            @RequestBody EvaluationRegisterRequestDto evaluationRegisterRequestDto,
            @Parameter(hidden = true) @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return OK(evaluationService.saveEvaluation(evaluationRegisterRequestDto, userPrincipal.getId()));
    }

}
