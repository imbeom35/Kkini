package com.kkini.core.domain.comment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Schema(description = "댓글 조회 응답 DTO")
public class CommentListResponseDto {

    @Schema(description = "댓글 ID")
    private Long id;

    @Schema(description = "부모 댓글 ID")
    private Long parentsId;

    @Schema(description = "작성자 ID")
    private Long memberId;

    @Schema(description = "작성자 이름")
    private String nickname;

    @Schema(description = "작성자 프로필 이미지")
    private String memberImage;
    
    @Schema(description = "작성일")
    private Timestamp createDatetime;

    @Schema(description = "내용")
    private String contents;

}
