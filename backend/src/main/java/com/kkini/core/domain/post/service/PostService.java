package com.kkini.core.domain.post.service;

import com.kkini.core.domain.post.dto.request.PostRegisterRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {

    void savePost(PostRegisterRequestDto dto, List<MultipartFile> multipartFiles, Long memberId);

    void removePost(Long postId, Long memberId);

}
