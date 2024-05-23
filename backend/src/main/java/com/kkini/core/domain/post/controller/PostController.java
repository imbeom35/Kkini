package com.kkini.core.domain.post.controller;

import com.kkini.core.domain.oauth2.UserPrincipal;
import com.kkini.core.domain.post.dto.request.PostRegisterRequestDto;
import com.kkini.core.domain.post.dto.response.PostListResponseDto;
import com.kkini.core.domain.post.service.PostQueryService;
import com.kkini.core.domain.post.service.PostService;
import com.kkini.core.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static com.kkini.core.global.response.Response.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
@Slf4j
@Tag(name = "Post", description = "포스트 관리 API")
public class PostController {

    private final PostService postService;
    private final PostQueryService postQueryService;

    @Operation(summary = "포스트 작성", description = "포스트를 작성한다.")
    @Parameters({
            @Parameter(name = "postRegisterRequestDto", description = "포스트 정보"),
            @Parameter(name = "multipartFiles", description = "이미지")
    })
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public Response<Void> addPost(
            @RequestPart(value = "data") PostRegisterRequestDto postRegisterRequestDto,
            @RequestPart(value = "files") List<MultipartFile> multipartFiles,
            @Parameter(hidden = true) @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        postService.savePost(postRegisterRequestDto, multipartFiles, userPrincipal.getId());

        return OK(null);
    }

    @Operation(summary = "포스트 상세 조회", description = "포스트 한개를 상세 조회한다.")
    @Parameter(name = "postId", description = "postID 정보")
    @GetMapping("/detail/{postId}")
    public Response<PostListResponseDto> getFeedPostList(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return OK(postQueryService.getPostDetail(postId, userPrincipal.getId()));
    }

    @Operation(summary = "포스트 목록 조회 : 피드", description = "피드 탭의 포스트를 조회한다.")
    @Parameter(name = "pageable", description = "페이지 정보")
    @GetMapping
    public Response<Page<PostListResponseDto>> getFeedPostList(
            @PageableDefault(sort="modifyDateTime", direction = Sort.Direction.DESC) Pageable pageable,
            @Parameter(hidden = true) @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return OK(postQueryService.getPostList(pageable, userPrincipal.getId()));
    }

    @Operation(summary = "포스트 목록 조회 : 마이페이지", description = "마이페이지 탭의 포스트를 조회한다.")
    @Parameter(name = "pageable", description = "페이지 정보")
    @GetMapping("/{id}")
    public Response<Page<PostListResponseDto>> getMyPagePostList(
            @PageableDefault(sort="modifyDateTime", direction = Sort.Direction.DESC) Pageable pageable,
            @Parameter(hidden = true) @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable("id") String memberId
    ) {
        Page<PostListResponseDto> dtoPage;

        if (memberId.equals("mypage")) {
            dtoPage = postQueryService.getMyPagePostList(pageable, userPrincipal.getId());
        } else {
            dtoPage = postQueryService.getMyPagePostList(pageable, Long.parseLong(memberId));
        }

        return OK(dtoPage);
    }

    @Operation(summary = "포스트 목록 조회 : 검색", description = "검색 탭의 검색 포스트를 조회한다.")
    @Parameters({
            @Parameter(name = "pageable", description = "페이지 정보"),
            @Parameter(name = "search", description = "검색어")
    })
    @GetMapping("/search")
    public Response<Page<PostListResponseDto>> getSearchPostList(
            @PageableDefault(sort="modifyDateTime", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam String search,
            @Parameter(hidden = true) @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return OK(postQueryService.getSearchPostList(pageable, userPrincipal.getId(), search));
    }

    @Operation(summary = "포스트 목록 조회 : 추천", description = "검색 탭의 추천 포스트를 조회한다.")
    @Parameters({
            @Parameter(name = "pageable", description = "페이지 정보"),
            @Parameter(name = "search", description = "검색어")
    })
    @GetMapping("/algorithm")
    public Response<Page<PostListResponseDto>> getAlgorithmPostList(
            int page,
            @Parameter(hidden = true) @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return OK(postQueryService.getAlgorithmPostList(page, userPrincipal.getId()));
    }

    @Operation(summary = "포스트 삭제", description = "포스트를 삭제한다.")
    @Parameter(name = "id", description = "포스트 식별자")
    @DeleteMapping("/{id}")
    public Response<Void> removePost(
            @PathVariable("id") Long id,
            @Parameter(hidden = true) @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        postService.removePost(id, userPrincipal.getId());

        return OK(null);
    }

}
