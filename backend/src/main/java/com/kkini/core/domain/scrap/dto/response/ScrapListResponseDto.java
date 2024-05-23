package com.kkini.core.domain.scrap.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(description = "스크랩 리스트 응답 dto")
@AllArgsConstructor
public class ScrapListResponseDto {

    @Schema(description = "스크랩 식별자")
    private Long id;

    @Schema(description = "포스트 식별자")
    private Long postId;

    @Schema(description = "이미지")
    private String image;

    @Schema(description = "포스트 좋아요 수")
    private int likeCnt;

    @Schema(description = "포스트 싫어요 수")
    private int disLikeCnt;

}
