package com.kkini.core.domain.comment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "댓글 생성 요청 DTO")
public class CommentRegisterRequestDto {

    @Schema(description = "포스트")
    private Long postId;

    @Schema(description = "부모 댓글")
    private Long parentsId;

    @Schema(description = "내용")
    private String contents;

}