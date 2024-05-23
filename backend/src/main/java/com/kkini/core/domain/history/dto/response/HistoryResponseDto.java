package com.kkini.core.domain.history.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Schema(description = "내 검색 기록 리스트 조회 필드")
public class HistoryResponseDto {

    @Schema(description = "검색했던 단어의 ID")
    private Long historyId;

    @Schema(description = "검색했던 단어")
    private String word;

    @Schema(description = "검색했던 시간")
    private Timestamp searchedTime;

}
