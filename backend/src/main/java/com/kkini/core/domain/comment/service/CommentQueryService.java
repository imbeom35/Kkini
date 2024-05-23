package com.kkini.core.domain.comment.service;

import com.kkini.core.domain.comment.dto.response.CommentListStructureResponseDto;

import java.util.List;

public interface CommentQueryService {

    List<CommentListStructureResponseDto> getCommentListStructure(Long postId);

}
