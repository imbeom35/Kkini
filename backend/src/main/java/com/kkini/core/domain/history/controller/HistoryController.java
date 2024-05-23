package com.kkini.core.domain.history.controller;

import com.kkini.core.domain.history.dto.response.HistoryResponseDto;
import com.kkini.core.domain.history.service.HistoryQueryService;
import com.kkini.core.domain.history.service.HistoryService;
import com.kkini.core.domain.oauth2.UserPrincipal;
import com.kkini.core.domain.recipe.dto.request.RecipeRegisterRequestDto;
import com.kkini.core.domain.recipe.dto.request.SearchConditionRequestDto;
import com.kkini.core.domain.recipe.dto.response.RecipeDetailResponseDto;
import com.kkini.core.domain.recipe.dto.response.RecipeListResponseDto;
import com.kkini.core.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.kkini.core.global.response.Response.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/history")
@Slf4j
@Tag(name = "History", description = "History 관리 API")
public class HistoryController {

    private final HistoryService historyService;
    private final HistoryQueryService historyQueryService;

    @Operation(summary = "내 최근 검색어 리스트 조회", description = "내 최근 검색어 리스트를 조회하는 API입니다. 최근에 검색했던 검색 단어 10개를 보내줍니다.")
    @Parameters({
    })
    @GetMapping
    public Response<List<HistoryResponseDto>> getHistoryList(@Parameter(hidden = true) @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return OK(historyQueryService.getMyHistoryList(userPrincipal.getId()));
    }

    @Operation(summary = "최근 검색어 삭제", description = "최근 검색어 하나를 삭제하는 API입니다.")
    @Parameters({
            @Parameter(name = "id", description = "검색어 ID")
    })
    @DeleteMapping("/{id}")
    public Response<Void> removeHistory(@PathVariable("id") Long historyId) {
        historyService.removeOne(historyId);
        return OK(null);
    }

    @Operation(summary = "최근 검색어 일괄 삭제", description = "최근 검색어 전체를 삭제하는 API입니다.")
    @Parameters({
    })
    @DeleteMapping()
    public Response<Void> removeAllHistory(@Parameter(hidden = true) @AuthenticationPrincipal UserPrincipal userPrincipal) {
        historyService.removeAll(userPrincipal.getId());
        return OK(null);
    }

}
