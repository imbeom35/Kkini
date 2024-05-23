package com.kkini.core.domain.history.service;

public interface HistoryService {
    void removeOne(Long id);

    void removeAll(Long memberId);
}
