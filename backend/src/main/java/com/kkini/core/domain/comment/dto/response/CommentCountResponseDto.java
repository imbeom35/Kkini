package com.kkini.core.domain.comment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "댓글 개수 응답 DTO")
public class CommentCountResponseDto {

    @Schema(description = "포스트 ID")
    private int id;

    @Schema(description = "댓글 개수")
    private int count;
}
