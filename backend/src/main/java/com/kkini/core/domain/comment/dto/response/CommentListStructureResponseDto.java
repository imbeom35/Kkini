package com.kkini.core.domain.comment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Schema(description = "댓글 조회 응답 DTO")
public class CommentListStructureResponseDto {
    
    @Schema(description = "부모 댓글")
    private CommentListResponseDto parents;

    @Schema(description = "자식 댓글")
    private List<CommentListResponseDto> children;

}
