package com.kkini.core.domain.comment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "댓글 변경 요청 DTO")
public class CommentUpdateRequestDto {

    @Schema(description = "댓글 ID")
    private Long commentId;
    
    @Schema(description = "내용")
    private String contents;

}