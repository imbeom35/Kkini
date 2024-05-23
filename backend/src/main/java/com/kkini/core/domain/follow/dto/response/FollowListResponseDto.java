package com.kkini.core.domain.follow.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(name = "팔로우 관련 응답 리스트 dto", description = "팔로우 관련 리스트를 응답하는 경우 사용하는 dto")
@AllArgsConstructor
public class FollowListResponseDto {

    @Schema(name = "식별자")
    private Long id;

    @Schema(name = "회원 식별자")
    private Long memberId;

    @Schema(name = "닉네임")
    private String nickname;

    @Schema(name = "프로필 이미지")
    private String image;

}