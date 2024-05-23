package com.kkini.core.domain.scrap.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "스크랩 추가 요청 처리 dto")
public class AddScrapRequestDto {

    @Schema(description = "멤버 식별자")
    private Long memberId;

    @Schema(description = "포스트 식별자")
    private Long postId;


}