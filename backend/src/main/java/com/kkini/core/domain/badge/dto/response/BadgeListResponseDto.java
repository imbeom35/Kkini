package com.kkini.core.domain.badge.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Schema(description = "내 뱃지 리스트 조회 필드")
public class BadgeListResponseDto {

    @Schema(description = "뱃지 ID")
    private Long badgeId;

    @Schema(description = "뱃지 이름")
    private String name;

    @Schema(description = "뱃지 이미지")
    private String image;

    @Schema(description = "뱃지 획득일")
    private Timestamp achieveDate;

}
