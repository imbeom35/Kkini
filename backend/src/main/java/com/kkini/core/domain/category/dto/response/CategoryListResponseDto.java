package com.kkini.core.domain.category.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "레시피 카테고리 리스트 조회 필드")
public class CategoryListResponseDto {

    @Schema(description = "카테고리 ID")
    private Long id;

    @Schema(description = "카테고리 이름")
    private String name;

}
