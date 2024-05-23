package com.kkini.core.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(name = "회원 검색 결과 응답 리스트 dto", description = "회원 검색 결과를 응답하는 경우 사용")
@AllArgsConstructor
public class SearchMemberResponseDto {
    
    @Schema(name = "회원 식별자")
    private Long memberId;

    @Schema(name = "이름")
    private String name;

    @Schema(name = "닉네임")
    private String nickname;

    @Schema(name = "프로필 이미지")
    private String image;
}
