package com.kkini.core.global.config.security;

import com.kkini.core.domain.oauth2.jwt.JwtAuthenticationFilter;
import com.kkini.core.domain.oauth2.jwt.JwtTokenProvider;
import com.kkini.core.domain.oauth2.handler.OAuth2AuthenticationFailureHandler;
import com.kkini.core.domain.oauth2.handler.OAuth2AuthenticationSuccessHandler;
import com.kkini.core.domain.oauth2.lib.CookieAuthorizationRequestRepository;
import com.kkini.core.domain.oauth2.lib.CookieUtils;
import com.kkini.core.domain.oauth2.service.CustomOAuth2UserService;
import com.kkini.core.domain.oauth2.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@Slf4j
public class WebSecurityConfigure {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final JwtTokenProvider jwtTokenProvider;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
    private final CustomUserDetailsService customUserDetailsService;

    @Value("${url.frontend}")
    private String FRONTEND_BASE_URL;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.debug("filterChain => ");
        log.debug("   {}", http.toString());
        //httpBasic, csrf, formLogin, rememberMe, logout, session disable
        http
//                .cors()
//                .and()
                .csrf().disable()
                .formLogin().disable()
                .rememberMe().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        //요청에 대한 권한 설정
        http.authorizeRequests()
                .antMatchers("/","/api-docs/**","/api/**","/api/swagger-ui/index.html", "/api/swagger.html","/js/**","/css/**").permitAll()
                .anyRequest().authenticated();

        //oauth2Login
        http.oauth2Login()
                .authorizationEndpoint()
                .baseUri("/api/oauth2/authorize")  // 소셜 로그인 url
                .authorizationRequestRepository(cookieAuthorizationRequestRepository)  // 인증 요청을 cookie 에 저장
                .and()
                .redirectionEndpoint()
                .baseUri("/api/oauth2/callback/*")  // 소셜 인증 후 redirect url
                .and()
                .userInfoEndpoint()
                .userService(customOAuth2UserService)  // 회원 정보 처리
                //userService()는 OAuth2 인증 과정에서 Authentication 생성에 필요한 OAuth2User 를 반환하는 클래스를 지정한다.
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler);

        http.logout()
                .logoutUrl("/api/member/logout")
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .logoutSuccessHandler((request, response, authentication) ->
                        response.sendRedirect(FRONTEND_BASE_URL)
                );


        //jwt filter 설정
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, customUserDetailsService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
