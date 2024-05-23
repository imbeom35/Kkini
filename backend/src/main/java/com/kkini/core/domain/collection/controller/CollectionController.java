package com.kkini.core.domain.collection.controller;

import com.kkini.core.domain.collection.dto.response.CollectionListResponseDto;
import com.kkini.core.domain.collection.service.CollectionQueryService;
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
@RequestMapping("/api/collection")
@Slf4j
@Tag(name = "Collection", description = "도감 관리 API")
public class CollectionController {

    private final CollectionQueryService collectionQueryService;

    @Operation(summary = "내 도감 리스트 조회", description = "내 도감 리스트를 조회하는 API입니다.")
    @Parameters({
    })
    @GetMapping
    public Response<List<CollectionListResponseDto>> getMyCollection(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return OK(collectionQueryService.getMyCollection(userPrincipal.getId()));
    }

}
