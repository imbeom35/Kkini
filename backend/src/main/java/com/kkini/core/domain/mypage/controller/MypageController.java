package com.kkini.core.domain.mypage.controller;

import com.kkini.core.domain.follow.dto.response.FollowListResponseDto;
import com.kkini.core.domain.follow.service.FollowQueryService;
import com.kkini.core.domain.follow.service.FollowService;
import com.kkini.core.domain.mypage.dto.response.MypageInfoResponseListDto;
import com.kkini.core.domain.mypage.service.MypageQueryService;
import com.kkini.core.domain.mypage.service.MypageService;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.kkini.core.global.response.Response.OK;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/mypage")
@Tag(name = "Mypage", description = "마이페이지 API")
public class MypageController {

    private final MypageQueryService mypageQueryService;
    private final MypageService mypageService;
    private final FollowService followService;
    private final FollowQueryService followQueryService;

    @Operation(summary = "마이페이지 정보", description = "멤버 식별자의 마이페이지 정보를 출력합니다.")
    @Parameter(name = "memberId", description = "정보를 조회할 멤버 식별자")
    @GetMapping("/info/{memberId}")
    public Response<MypageInfoResponseListDto> getOtherpageInfo(@PathVariable String memberId, @AuthenticationPrincipal UserPrincipal userPrincipal){
        log.debug("## 마이페이지 정보를 보여줍니다.");
        log.debug("조회할 멤버 식별자 : {}",memberId);

        MypageInfoResponseListDto mypageInfoResponseListDto = null;

        if (memberId.equals("mypage")){
            mypageInfoResponseListDto = mypageQueryService.getMypageInfo(userPrincipal.getId());
        } else{
            mypageInfoResponseListDto = mypageQueryService.getMypageInfo(Long.parseLong(memberId));
        }

        return OK(mypageInfoResponseListDto);
    }


    @Operation(summary = "프로필 이미지", description = "해당 유저의 프로필 이미지를 불러옵니다.")
    @GetMapping("/profileImage/{memberId}")
    public Response<String> getOtherProfileImage(@PathVariable String memberId, @AuthenticationPrincipal UserPrincipal userPrincipal){
        log.debug("## 프로필 이미지를 불러옵니다.");
        log.debug("멤버 식별자 : {}", memberId);

        String image = "";

        if (memberId.equals("mypage")){
            image = mypageQueryService.getProfileImage(userPrincipal.getId());
        } else {
            image = mypageQueryService.getProfileImage(Long.parseLong(memberId));
        }

        log.debug("불러온 프로필 이미지 : {}", image);

        return OK(image);
    }

    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴를 진행합니다.")
    @DeleteMapping("/withdrawal")
    public Response<Void> withDrawalMembership(@Parameter(hidden = true) @AuthenticationPrincipal UserPrincipal userPrincipal){
        log.debug("## 회원 탈퇴를 진행합니다.");
        log.debug("탈퇴를 진행할 회원 : {}", userPrincipal.getId());
        mypageService.withDrawalMembership(userPrincipal.getId());
        return OK(null);
    }


}
