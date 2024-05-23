package com.kkini.core.domain.recipe.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "검색 조건 필드")
public class SearchConditionRequestDto {

    @Schema(description = "카테고리 ID")
    private Long categoryId;

    @Schema(description = "레시피 제목")
    private String name;

}