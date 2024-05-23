package com.kkini.core.domain.member.entity;

import com.kkini.core.domain.oauth2.enums.AuthProvider;
import com.kkini.core.domain.oauth2.enums.Role;
import com.kkini.core.domain.oauth2.userinfo.OAuth2UserInfo;
import com.kkini.core.global.entity.BaseEntityWithModifiedTime;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(indexes = {
        @Index(name = "idx_email", columnList = "email"),
        @Index(name = "idx_nickname", columnList = "nickname")
})
@Entity
public class Member extends BaseEntityWithModifiedTime {

    private String email;

    private String name;

    private String nickname;

    private String refreshToken;

    @ColumnDefault("1")
    private int level;

    private int stars;

    private String image;

    private String oauth2Id;

    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Member update(OAuth2UserInfo oAuth2UserInfo) {
        this.name = oAuth2UserInfo.getName();
        this.oauth2Id = oAuth2UserInfo.getOAuth2Id();

        return this;
    }

    public void updateRefreshToken(String token) {
        this.refreshToken = token;
    }

    public void addStars(int cnt) {
        this.stars += cnt;
    }

    public void loseStars(int cnt) {
        this.stars -= cnt;
    }

    public void levelUp() {
        this.level++;
    }
}
