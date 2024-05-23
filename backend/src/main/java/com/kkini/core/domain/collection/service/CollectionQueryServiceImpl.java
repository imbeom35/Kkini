package com.kkini.core.domain.collection.service;

import com.kkini.core.domain.collection.dto.response.CollectionListResponseDto;
import com.kkini.core.domain.collection.repository.CollectionQueryRepository;
import com.kkini.core.domain.member.entity.Member;
import com.kkini.core.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CollectionQueryServiceImpl implements CollectionQueryService {

    private final CollectionQueryRepository collectionQueryRepository;

    @Override
    public List<CollectionListResponseDto> getMyCollection(Long memberId) {
        return collectionQueryRepository.findRandomCollection(memberId);
    }
}
