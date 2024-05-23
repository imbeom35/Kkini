package com.kkini.core.domain.notification.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "알림 리스트 응답 dto")
public class NotificationListResponseDto {

    @Schema(description = "알림 식별자")
    private Long id;

    @Schema(description = "수신인 식별자")
    private Long receiver;

    @Schema(description = "송신인 식별자")
    private Long sender;

    @Schema(description = "송신인 닉네임")
    private Long senderNickname;

    @Schema(description = "포스트 식별자")
    private Long postId;

    @Schema(description = "알림 생성일")
    private String createDatetime;

    @Schema(description = "분류")
    private String category;

    @Schema(description = "확인 여부")
    private String checked;
}
