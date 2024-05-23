package com.kkini.core.domain.category.service;

import com.kkini.core.domain.category.dto.response.CategoryListResponseDto;
import com.kkini.core.domain.category.repository.CategoryQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryQueryServiceImpl implements CategoryQueryService {

    private final CategoryQueryRepository categoryQueryRepository;

    @Override
    public List<CategoryListResponseDto> getCategoryList() {
        return categoryQueryRepository.getAllCategories();
    }
}
