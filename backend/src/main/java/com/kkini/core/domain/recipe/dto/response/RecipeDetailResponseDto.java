package com.kkini.core.domain.recipe.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Data
@Schema(description = "레시피 상세 조회 필드")
public class RecipeDetailResponseDto {
    @Schema(description = "레시피 ID")
    private Long recipeId;

    @Schema(description = "카테고리 ID")
    private Long categoryId;

    @Schema(description = "카테고리 이름")
    private String categoryName;

    @Schema(description = "작성자 ID")
    private Long writerId;

    @Schema(description = "작성자 프로필 이미지")
    private String writerImage;

    @Schema(description = "작성자 이름")
    private String writerName;

    @Schema(description = "레시피 이름")
    private String name;

    @Schema(description = "소요시간")
    private int time;

    @Schema(description = "난이도")
    private int difficulty;

    @Schema(description = "재료")
    private String ingredient;

    @Schema(description = "대표 이미지")
    private String image;

    @Schema(description = "조리 과정")
    private String steps;

    public RecipeDetailResponseDto(Long recipeId, Long categoryId, String categoryName, Long writerId, String writerImage, String writerName, String name, int time, int difficulty, String ingredient, String image, String steps) {
        this.recipeId = recipeId;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.writerId = writerId;
        this.writerImage = writerImage;
        this.writerName = writerName;
        this.name = name;
        this.time = time;
        this.difficulty = difficulty;
        this.ingredient = ingredient;
        this.image = image;
        this.steps = steps;
    }
}
