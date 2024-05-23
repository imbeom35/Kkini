package com.kkini.core.domain.oauth2.service;


import com.kkini.core.domain.member.entity.Member;
import com.kkini.core.domain.oauth2.enums.AuthProvider;
import com.kkini.core.domain.oauth2.enums.Role;
import com.kkini.core.domain.oauth2.userinfo.OAuth2UserInfo;
import com.kkini.core.domain.oauth2.userinfo.OAuth2UserInfoFactory;
import com.kkini.core.domain.oauth2.UserPrincipal;
import com.kkini.core.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        log.debug("loaduser =>");
        log.debug(" {}", oAuth2UserRequest);
        OAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
        log.debug(" {}", oAuth2UserService);
        OAuth2User oAuth2User = oAuth2UserService.loadUser(oAuth2UserRequest);
        log.debug(" {}", oAuth2User);

        return processOAuth2User(oAuth2UserRequest, oAuth2User);
    }

    protected OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        log.debug("processOAuth2User =>");
        log.debug(" {}", oAuth2UserRequest);
        log.debug(" {}", oAuth2User);
        //OAuth2 로그인 플랫폼 구분
        AuthProvider authProvider = AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase());
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(authProvider, oAuth2User.getAttributes());
        log.debug(" {}", authProvider);
        log.debug("유저 정보 : {}", oAuth2UserInfo.getAttributes());

        if (!StringUtils.hasText(oAuth2UserInfo.getEmail())) {
            throw new RuntimeException("Email not found from OAuth2 provider");
        }

        Member member = memberRepository.findByEmail(oAuth2UserInfo.getEmail()).orElse(null);
        log.debug(" {}", member);
        //이미 가입된 경우
        if (member != null) {
            log.warn("이미 가입된 경우!!");
            if (!member.getAuthProvider().equals(authProvider)) {
                throw new RuntimeException("Email already signed up.");
            }
            member = updateUser(member, oAuth2UserInfo);
        }
        //가입되지 않은 경우
        else {
            member = registerUser(authProvider, oAuth2UserInfo);
        }

        return UserPrincipal.create(member, oAuth2UserInfo.getAttributes());
    }

    private Member registerUser(AuthProvider authProvider, OAuth2UserInfo oAuth2UserInfo) {
        log.debug("{}", oAuth2UserInfo.getAttributes());

        Member member = Member.builder()
                .level(1)
                .email(oAuth2UserInfo.getEmail())
                .image(oAuth2UserInfo.profile_imgae())
                .name(oAuth2UserInfo.getName())
                .nickname(oAuth2UserInfo.getNickname())
                .oauth2Id(oAuth2UserInfo.getOAuth2Id())
                .authProvider(authProvider)
                .role(Role.ROLE_USER)
                .build();
        log.debug("registerUser =>");
        log.debug(" {}", member.getLevel());

        return memberRepository.save(member);
    }

    private Member updateUser(Member member, OAuth2UserInfo oAuth2UserInfo) {
        log.debug("updateUser =>");
        log.debug(" {}", member);
        log.debug(" {}", oAuth2UserInfo);
        return member.update(oAuth2UserInfo);
    }
}
