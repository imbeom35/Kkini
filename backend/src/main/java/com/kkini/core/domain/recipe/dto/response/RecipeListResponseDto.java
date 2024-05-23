package com.kkini.core.domain.recipe.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "레시피 리스트 조회 필드")
public class RecipeListResponseDto {
    @Schema(description = "레시피 ID")
    private Long recipeId;

    @Schema(description = "작성자 ID")
    private Long writerId;

    @Schema(description = "작성자 프로필 이미지")
    private String writerImage;

    @Schema(description = "작성자 이름")
    private String writerName;

    @Schema(description = "레시피 대표 이미지")
    private String recipeImage;

    @Schema(description = "레시피 이름")
    private String recipeName;

    @Schema(description = "난이도")
    private int difficulty;

}
