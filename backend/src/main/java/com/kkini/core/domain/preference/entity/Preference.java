package com.kkini.core.domain.preference.entity;

import com.kkini.core.domain.category.entity.Category;
import com.kkini.core.domain.member.entity.Member;
import com.kkini.core.global.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@SuperBuilder
public class Preference extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    private int weight;

    public void increaseWeightByEval() { this.weight++; }

    public void decreaseWeightByEval() { this.weight--; }

    public void increaseWeightByLike() { this.weight += 3; }

    public void decreaseWeightByLike() { this.weight -= 3; }

}
