package com.kkini.core.domain.scrap.service;

import com.kkini.core.domain.scrap.dto.response.ScrapListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScrapQueryService {

    // 스크랩 리스트 조회
    Page<ScrapListResponseDto> getScrapList(Long memberId, Pageable pageable);

}
