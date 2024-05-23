package com.kkini.core.domain.follow.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "팔로우 관련 요청 dto", description = "팔로우 관련 요청하는 경우 사용하는 dto")
public class FollowRequestDto {

    @Schema(description = "회원 식별자")
    private Long memberId;

    @Schema(description = "타겟 회원 식별자")
    private Long targetMemberId;
}