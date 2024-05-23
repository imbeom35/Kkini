package com.kkini.core.domain.member.controller;

import com.kkini.core.domain.member.dto.SearchMemberResponseDto;
import com.kkini.core.domain.member.service.MemberQueryService;
import com.kkini.core.global.response.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.config.web.servlet.oauth2.client.OAuth2ClientSecurityMarker;
import org.springframework.security.config.web.servlet.oauth2.login.OAuth2LoginSecurityMarker;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/member")
@Tag(name = "Member", description = "멤버 관리 API")
public class MemberController {

    private final MemberQueryService memberQueryService;
    @GetMapping("/search/{query}")
    public Response<Page<SearchMemberResponseDto>> searchQeury(@PathVariable String query, @PageableDefault Pageable pageable){
        log.debug("## 회원 닉네임을 검색합니다.");
        log.debug("검색어 : {}", query);

        Page<SearchMemberResponseDto> list = memberQueryService.searchMember(query, pageable);

        log.debug("닉네임 검색 결과 : {}", list);

        return Response.OK(list);
    }
}
