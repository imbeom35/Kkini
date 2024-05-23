package com.kkini.core.domain.recipe.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Schema(description = "레시피 등록 필드")
public class RecipeRegisterRequestDto {

    @Schema(description = "카테고리 ID")
    private Long categoryId;

    @Schema(description = "레시피 이름")
    private String name;

    @Schema(description = "소요시간")
    private int time;

    @Schema(description = "재료")
    private String ingredient;

    @Schema(description = "조리 과정")
    private String steps;

}
