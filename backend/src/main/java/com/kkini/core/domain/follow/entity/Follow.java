package com.kkini.core.domain.follow.entity;

import com.kkini.core.domain.member.entity.Member;
import com.kkini.core.global.entity.BaseEntityWithCreatedTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
public class Follow extends BaseEntityWithCreatedTime {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "me_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member me;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member target;
}
