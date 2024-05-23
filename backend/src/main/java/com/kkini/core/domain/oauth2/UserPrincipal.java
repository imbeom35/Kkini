package com.kkini.core.domain.oauth2;

import com.kkini.core.domain.member.entity.Member;
import com.kkini.core.domain.oauth2.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
@Slf4j
public class UserPrincipal implements OAuth2User, UserDetails {

    private Long id;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    @Setter
    private Map<String, Object> attributes;

    public UserPrincipal(Long id, String email, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.authorities = authorities;
    }

    public static UserPrincipal create(Member member) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(Role.ROLE_USER.name()));
        return new UserPrincipal(
                member.getId(),
                member.getEmail(),
                authorities
        );
    }

    public static UserPrincipal create(Member member, Map<String, Object> attributes) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(Role.ROLE_USER.name()));
        UserPrincipal userPrincipal = new UserPrincipal(member.getId(), member.getEmail(), authorities);
        userPrincipal.setAttributes(attributes);
        // 이 부분에서 setter로 설정하는 부분에서 set이 되지 않아서 get이 null입니다.
        // 오류는 발생하지 않습니다.
        return userPrincipal;
    }
    @Override
    public String getUsername() {
        return email;
    }
    // 이 부분에서 Username을 email로 설정하는 부분에서 시큐리티의 User를 가져왔을 때, email이 세팅된 부분을 가져오는 것 같은데
    // 왜 위에서는 create로 설정이 안 되는지는 모르겠습니다.......

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getName() {
        return String.valueOf(id);
    }

}
