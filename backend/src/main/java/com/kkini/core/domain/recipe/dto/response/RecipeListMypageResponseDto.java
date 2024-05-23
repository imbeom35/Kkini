package com.kkini.core.domain.recipe.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "내 레시피 리스트 조회 필드")
public class RecipeListMypageResponseDto {
    @Schema(description = "레시피 ID")
    private Long recipeId;

    @Schema(description = "레시피 대표 이미지")
    private String recipeImage;

    @Schema(description = "작성 회원 닉네임")
    private String recipeName;

}
