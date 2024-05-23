package com.kkini.core.domain.collection.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "도감 리스트 조회 필드")
public class CollectionListResponseDto {

    @Schema(description = "레시피 ID")
    private Long recipeId;

    @Schema(description = "레시피 이미지")
    private String image;

}
