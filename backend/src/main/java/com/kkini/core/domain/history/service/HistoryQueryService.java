package com.kkini.core.domain.history.service;

import com.kkini.core.domain.history.dto.response.HistoryResponseDto;

import java.util.List;

public interface HistoryQueryService {
    List<HistoryResponseDto> getMyHistoryList(Long memberId);
}
