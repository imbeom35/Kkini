package com.kkini.core.domain.own.controller;

import com.kkini.core.domain.own.service.OwnService;
import com.kkini.core.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.kkini.core.global.response.Response.OK;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/own")
@Tag(name = "Own", description = "내 뱃지 관리 API")
public class OwnController {

    private final OwnService ownService;

    @Operation(summary = "착용 뱃지 변경", description = "착용한 뱃지를 변경하는 API입니다.")
    @Parameters({
            @Parameter(name = "oldId", description = "착용중이던 뱃지의 ID"),
            @Parameter(name = "newId", description = "새롭게 착용할 뱃지의 ID")
    })
    @PutMapping("/{oldId}/{newId}")
    public Response<Void> getMyBadgeList(@PathVariable("oldId") Long oldId, @PathVariable("newId") Long newId) {
        ownService.changeSelectedBadge(oldId, newId);
        return OK(null);
    }

}
