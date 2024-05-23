package com.kkini.core.domain.category.entity;

import com.kkini.core.global.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
public class Category extends BaseEntity {

    private String name;

    private String image;

}
