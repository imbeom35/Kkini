package com.kkini.core.domain.category.controller;

import com.kkini.core.domain.category.dto.response.CategoryListResponseDto;
import com.kkini.core.domain.category.service.CategoryQueryService;
import com.kkini.core.domain.recipe.dto.request.RecipeRegisterRequestDto;
import com.kkini.core.domain.recipe.dto.request.SearchConditionRequestDto;
import com.kkini.core.domain.recipe.dto.response.RecipeDetailResponseDto;
import com.kkini.core.domain.recipe.dto.response.RecipeListResponseDto;
import com.kkini.core.domain.recipe.service.RecipeQueryService;
import com.kkini.core.domain.recipe.service.RecipeService;
import com.kkini.core.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kkini.core.global.response.Response.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
@Slf4j
@Tag(name = "Category", description = "레시피 카테고리 관리 API")
public class CategoryController {

    private final CategoryQueryService categoryQueryService;

    @Operation(summary = "카테고리 리스트 조회", description = "카테고리 리스트를 조회하는 API입니다. 레시피를 작성할 때 사용할 수 있습니다.")
    @Parameters({
    })
    @GetMapping
    public Response<List<CategoryListResponseDto>> getAllCategory() {
        return OK(categoryQueryService.getCategoryList());
    }

}
