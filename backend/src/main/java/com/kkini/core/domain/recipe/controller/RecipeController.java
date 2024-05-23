package com.kkini.core.domain.recipe.controller;

import com.kkini.core.domain.oauth2.UserPrincipal;
import com.kkini.core.domain.post.dto.response.PostListResponseDto;
import com.kkini.core.domain.recipe.dto.request.RecipeRegisterRequestDto;
import com.kkini.core.domain.recipe.dto.request.SearchConditionRequestDto;
import com.kkini.core.domain.recipe.dto.response.RecipeAllListResponseDto;
import com.kkini.core.domain.recipe.dto.response.RecipeDetailResponseDto;
import com.kkini.core.domain.recipe.dto.response.RecipeListMypageResponseDto;
import com.kkini.core.domain.recipe.dto.response.RecipeListResponseDto;
import com.kkini.core.domain.recipe.service.RecipeQueryService;
import com.kkini.core.domain.recipe.service.RecipeService;
import com.kkini.core.global.response.Response;
import com.kkini.core.global.util.S3Util;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static com.kkini.core.global.response.Response.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recipe")
@Slf4j
@Tag(name = "Recipe", description = "레시피 관리 API")
public class RecipeController {

    private final RecipeQueryService recipeQueryService;
    private final RecipeService recipeService;

    @Operation(summary = "레시피 리스트 검색", description = "레시피 리스트를 검색하는 API입니다. page 기본값은 0, size 기본값은 10, sort 기본값은 'modifyDateTime, desc'입니다.")
    @Parameters({
            @Parameter(name = "searchConditionRequestDto", description = "검색 조건 필드"),
            @Parameter(name = "pageable", description = "페이지네이션 정보")
    })
    @GetMapping("/search")
    public Response<Page<RecipeListResponseDto>> getRecipeList(@ModelAttribute SearchConditionRequestDto searchConditionRequestDto, @PageableDefault(sort="modifyDateTime", direction = Sort.Direction.DESC) Pageable pageable) {
        return OK(recipeQueryService.getRecipeList(searchConditionRequestDto, pageable));
    }

    @Operation(summary = "내가 작성한 레시피 리스트 조회", description = "내가 작성한 레시피 리스트를 조회하는 API입니다. page 기본값은 0, size 기본값은 10, sort 기본값은 'modifyDateTime, desc'입니다.")
    @Parameters({
            @Parameter(name = "pageable", description = "페이지네이션 정보")
    })
    @GetMapping("/mypage/{id}")
    public Response<Page<RecipeListMypageResponseDto>> getRecipeList(
            @PageableDefault(sort="modifyDateTime", direction = Sort.Direction.DESC) Pageable pageable,
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable("id") String memberId)
    {
        Page<RecipeListMypageResponseDto> dtoPage;

        if (memberId.equals("mypage")) {
            dtoPage = recipeQueryService.getMyRecipeList(userPrincipal.getId(), pageable);
        } else {
            dtoPage = recipeQueryService.getMyRecipeList(Long.parseLong(memberId), pageable);
        }

        return OK(dtoPage);
    }

    @Operation(summary = "레시피 리스트 조회", description = "전체 레시피 리스트를 조회하는 API입니다.")
    @Parameters({
    })
    @GetMapping
    public Response<List<RecipeAllListResponseDto>> getAllRecipeList() {
        return OK(recipeQueryService.getAllRecipeList());
    }

    @Operation(summary = "레시피 상세 조회", description = "레시피 상세를 조회하는 API입니다.")
    @Parameters({
            @Parameter(name = "id", description = "레시피 ID")
    })
    @GetMapping("/{id}")
    public Response<RecipeDetailResponseDto> getRecipeDetail(@PathVariable("id") Long recipeId) {
        return OK(recipeQueryService.getRecipeDetail(recipeId));
    }

    @Operation(summary = "레시피 등록", description = "레시피를 등록하는 API입니다.")
    @Parameters({
            @Parameter(name = "recipeRegisterRequestDto", description = "레시피 등록 필드"),
            @Parameter(name = "file", description = "레시피 등록 필드")
    })
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public Response<Void> addRecipe(@RequestPart(value = "data") RecipeRegisterRequestDto recipeRegisterRequestDto,
                                    @RequestPart(value = "file") MultipartFile recipeImageFile,
                                    @AuthenticationPrincipal UserPrincipal userPrincipal) {
        recipeService.saveRecipe(recipeRegisterRequestDto, recipeImageFile, userPrincipal.getId());
        return OK(null);
    }

    @Operation(summary = "레시피 삭제", description = "레시피를 삭제하는 API입니다.")
    @Parameters({
            @Parameter(name = "id", description = "레시피 ID")
    })
    @DeleteMapping("/{id}")
    public Response<Void> removeRecipe(@PathVariable("id") Long recipeId,
                                       @AuthenticationPrincipal UserPrincipal userPrincipal) {
        recipeService.removeRecipe(recipeId, userPrincipal.getId());
        return OK(null);
    }

}
