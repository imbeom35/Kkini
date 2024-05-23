package com.kkini.core.domain.category.service;

import com.kkini.core.domain.category.dto.response.CategoryListResponseDto;

import java.util.List;

public interface CategoryQueryService {
    List<CategoryListResponseDto> getCategoryList();
}
