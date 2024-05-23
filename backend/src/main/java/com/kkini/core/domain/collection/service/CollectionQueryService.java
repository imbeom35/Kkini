package com.kkini.core.domain.collection.service;

import com.kkini.core.domain.collection.dto.response.CollectionListResponseDto;

import java.util.List;

public interface CollectionQueryService {
    List<CollectionListResponseDto> getMyCollection(Long memberId);
}
