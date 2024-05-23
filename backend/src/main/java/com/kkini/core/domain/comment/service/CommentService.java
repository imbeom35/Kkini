package com.kkini.core.domain.comment.service;

import com.kkini.core.domain.comment.dto.request.CommentRegisterRequestDto;
import com.kkini.core.domain.comment.dto.request.CommentUpdateRequestDto;

public interface CommentService {

    void saveComment(CommentRegisterRequestDto commentRegisterRequestDto, Long memberId);

    void modifyComment(CommentUpdateRequestDto commentUpdateRequestDto, Long memberId);

    void removeComment(Long commentId, Long memberId);

}
