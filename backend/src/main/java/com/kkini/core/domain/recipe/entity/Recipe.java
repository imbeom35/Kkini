package com.kkini.core.domain.recipe.entity;

import com.kkini.core.domain.category.entity.Category;
import com.kkini.core.domain.member.entity.Member;
import com.kkini.core.global.entity.BaseEntityWithModifiedTime;
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
public class Recipe extends BaseEntityWithModifiedTime {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    private String name;

    private int time;

    private int difficulty;

    private String ingredient;

    private String image;

    private String steps;

    private Boolean deleted;

    public void deleteRecipe() {
        this.deleted = true;
    }
}