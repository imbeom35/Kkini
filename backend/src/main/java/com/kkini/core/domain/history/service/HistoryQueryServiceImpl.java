package com.kkini.core.domain.history.service;

import com.kkini.core.domain.history.dto.response.HistoryResponseDto;
import com.kkini.core.domain.history.repository.HistoryQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class HistoryQueryServiceImpl implements HistoryQueryService {

    private final HistoryQueryRepository historyQueryRepository;

    @Override
    public List<HistoryResponseDto> getMyHistoryList(Long memberId) {
        return historyQueryRepository.findMyHistoryList(memberId);
    }
}
