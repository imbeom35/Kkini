package com.kkini.core.domain.follow.controller;

import com.kkini.core.domain.follow.dto.request.FollowRequestDto;
import com.kkini.core.domain.follow.dto.response.FollowListResponseDto;
import com.kkini.core.domain.follow.service.FollowQueryService;
import com.kkini.core.domain.follow.service.FollowService;
import com.kkini.core.domain.oauth2.UserPrincipal;
import com.kkini.core.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kkini.core.global.response.Response.ERROR;
import static com.kkini.core.global.response.Response.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/follow")
@Tag(name = "Follow", description = "팔로우 관리 API")
@Slf4j
public class FollowController {

    private final FollowQueryService followQueryService;
    private final FollowService followService;

    @Operation(summary = "팔로우 추가", description = "본인(memberId)이 팔로우(targetMemberId)를 추가합니다.")
    @Parameter(name = "targetMemberId", description = "팔로우를 하고 싶은 ID")
    @PostMapping("/{targetMemberId}")
    public Response<?> addFollow(@PathVariable Long targetMemberId, @Parameter(hidden = true)@AuthenticationPrincipal UserPrincipal userPrincipal){
        log.debug("## 팔로우를 추가합니다.");
        log.debug("대상 회원 : {}", targetMemberId);

        if (targetMemberId == userPrincipal.getId()){
            return ERROR("팔로우 안됨", HttpStatus.BAD_REQUEST);
        } else{
            FollowRequestDto followRequestDto = new FollowRequestDto();
            followRequestDto.setMemberId(userPrincipal.getId());
            followRequestDto.setTargetMemberId(targetMemberId);
            followService.addFollow(followRequestDto);
            return OK(null);
        }
    }

    @Operation(summary = "팔로우 삭제", description = "본인(memberId)이 팔로우(targetMemberId)를 삭제합니다.")
    @Parameter(name = "id", description = "팔로우 삭제를 하려는 팔로우 식별자")
    @DeleteMapping("/{targetId}")
    public Response<Void> deleteFollow(@PathVariable Long targetId, @AuthenticationPrincipal UserPrincipal userPrincipal){
        log.debug("## 팔로우를 삭제합니다.");
        log.debug("삭제할 팔로우 식별자 : {}", targetId);

        FollowRequestDto followRequestDto = new FollowRequestDto();
        followRequestDto.setMemberId(userPrincipal.getId());
        followRequestDto.setTargetMemberId(targetId);
        followService.deleteFollow(followRequestDto);

        return OK(null);
    }


    @Operation(summary = "팔로우 리스트", description = "회원(memberId)의 팔로우 리스트를 확인할 수 있습니다.")
    @Parameter(name = "memberId", description = "팔로우 리스트를 보고 싶은 회원(memberId)")
    @GetMapping("/followList/{memberId}")
    public Response<Page<FollowListResponseDto>> followList
            (@PathVariable String memberId, @Parameter(hidden = true)@AuthenticationPrincipal UserPrincipal userPrincipal,
             @PageableDefault(size = 50)Pageable pageable){
        log.debug("## 팔로우 리스트를 조회합니다.");
        log.debug("조회할 멤버 식별자 : {}", memberId);
        Page<FollowListResponseDto> followList = null;
        if (memberId.equals("mypage")){
            followList = followQueryService.getFollowList(userPrincipal.getId(), pageable);
        } else{
            followList = followQueryService.getFollowList(Long.parseLong(memberId), pageable);
        }

        log.debug("팔로우 리스트 : {}",followList);

        return OK(followList);
    }

    @Operation(summary = "팔로워 리스트", description = "회원(memberId)의 팔로워 리스트를 확인할 수 있습니다.")
    @Parameter(name = "memberId", description = "팔로워 리스트를 보고 싶은 회원(memberId)")
    @GetMapping("/followerList/{memberId}")
    public Response<Page<FollowListResponseDto>> followerList(
            @PathVariable String memberId, @Parameter(hidden = true) @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PageableDefault(size = 50) Pageable pageable){
        log.debug("## 팔로워 리스트를 조회합니다.");
        log.debug("조회할 멤버 식별자 : {}", memberId);
        Page<FollowListResponseDto> followerList = null;
        if (memberId.equals("mypage")){
            followerList = followQueryService.getFollowerList(userPrincipal.getId(), pageable);
        } else {
            followerList = followQueryService.getFollowerList(Long.parseLong(memberId), pageable);
        }
        log.debug("팔로워 리스트 : {}", followerList);
        return OK(followerList);
    }


    @Operation(summary = "팔로우 수 조회", description = "회원의 팔로우 수를 조회합니다.")
    @Parameter(name = "memberId", description = "대상 회원의 멤버 식별자")
    @GetMapping("/countFollow/{memberId}")
    public Response<Integer> countFollow(@PathVariable String memberId, @Parameter(hidden = true) @AuthenticationPrincipal UserPrincipal userPrincipal){
        log.debug("## 팔로우 수를 조회합니다.");
        log.debug("회원 식별자 : {}", memberId);
        int count = 0;
        if (memberId.equals("mypage")){
            count = followService.countFollows(userPrincipal.getId());
        }else{
            count = followService.countFollows(Long.parseLong(memberId));
        }
        log.debug("팔로우 수 : {}",count);

        return OK(count);
    }

    @Operation(summary = "팔로워 수 조회", description = "회원의 팔로워 수를 조회합니다.")
    @Parameter(name = "memberId", description = "대상 회원의 멤버 식별자")
    @GetMapping("/countFollower/{memberId}")
    public Response<Integer> countFollower(@PathVariable String memberId,@Parameter(hidden = true) @AuthenticationPrincipal UserPrincipal userPrincipal){
        log.debug("## 팔로워 수를 조회합니다.");
        log.debug("회원 식별자 : {}", memberId);
        int count = 0;
        if (memberId.equals("mypage")){
            count = followService.countFollowers(userPrincipal.getId());
        }else{
            count = followService.countFollowers(Long.parseLong(memberId));
        }
        log.debug("팔로워 수 : {}", count);

        return OK(count);
    }

    @Operation(summary = "팔로우 여부 확인", description = "팔로우 여부를 확인합니다.")
    @Parameter(name = "targetId", description = "타겟 멤버 식별자")
    @GetMapping("/isFollow/{targetId}")
    public Response<Integer> isFollow(@PathVariable String targetId, @Parameter(hidden = true) @AuthenticationPrincipal UserPrincipal userPrincipal) {
        log.debug("## 팔로우 여부를 확인합니다.");
        log.debug("타겟 식별자 : {}", targetId);

        int cnt;
        if (targetId.equals("mypage")){
            cnt = 0;
        } else {
            FollowRequestDto followRequestDto = new FollowRequestDto();
            followRequestDto.setMemberId(userPrincipal.getId());
            followRequestDto.setTargetMemberId(Long.parseLong(targetId));
            cnt = followService.isFollow(followRequestDto);
        }

        return OK(cnt);
    }


}
