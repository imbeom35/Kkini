package com.kkini.core.domain.evaluation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "평가 생성 요청 DTO")
public class EvaluationRegisterRequestDto {

    @Schema(description = "포스트 ID")
    private Long postId;

    @Schema(description = "금액")
    private int price;

}
