package com.kkini.core.domain.mypage.dto.response;

import com.kkini.core.domain.oauth2.enums.AuthProvider;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(name = "마이페이지 정보 응답 리스트 dto", description = "마이페이지 요청이 들어오면 한 번에 표시할 정보")
@Data
@AllArgsConstructor
public class MypageInfoResponseListDto {

    @Schema(name = "이름")
    private String name;

    @Schema(name = "닉네임")
    private String nickname;

    @Schema(name = "레벨")
    private int level;

    @Schema(name = "획득한 별")
    private int stars;
    
    @Schema(name = "프로필 이미지")
    private String image;

    @Schema(name = "이메일")
    private String email;

    @Schema(name = "가입 서비스")
    private AuthProvider authProvider;

}
