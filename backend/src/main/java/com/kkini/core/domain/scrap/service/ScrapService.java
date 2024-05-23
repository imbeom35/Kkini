package com.kkini.core.domain.scrap.service;

import com.kkini.core.domain.scrap.dto.request.AddScrapRequestDto;

public interface ScrapService {

    // 스크랩 추가
    void addScrap(AddScrapRequestDto addScrapRequestDto);
    
    // 스크랩 삭제
    void deleteScrap(Long id, Long memberId);

    // 스크랩 리스트 개수 조회
    int countScrapList(Long memberId);
}
