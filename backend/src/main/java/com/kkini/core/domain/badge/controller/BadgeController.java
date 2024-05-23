package com.kkini.core.domain.badge.controller;

import com.kkini.core.domain.badge.dto.response.BadgeListResponseDto;
import com.kkini.core.domain.badge.service.BadgeQueryService;
import com.kkini.core.domain.oauth2.UserPrincipal;
import com.kkini.core.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.kkini.core.global.response.Response.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/badge")
@Slf4j
@Tag(name = "Badge", description = "뱃지 관리 API")
public class BadgeController {

    private final BadgeQueryService badgeQueryService;

    @Operation(summary = "내 뱃지 리스트 조회", description = "내가 소유한 뱃지 리스트를 조회하는 API입니다.")
    @Parameters({
    })
    @GetMapping("/list")
    public Response<List<BadgeListResponseDto>> getMyBadgeList(@Parameter(hidden = true) @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return OK(badgeQueryService.getMyBadgeList(userPrincipal.getId()));
    }

}
