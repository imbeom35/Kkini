package com.kkini.core.domain.history.service;

import com.kkini.core.domain.history.entity.History;
import com.kkini.core.domain.history.repository.HistoryRepository;
import com.kkini.core.domain.member.entity.Member;
import com.kkini.core.domain.member.repository.MemberRepository;
import com.kkini.core.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;
    private final MemberRepository memberRepository;

    @Override
    public void removeOne(Long id) {
        History history = historyRepository.findById(id).orElseThrow(() -> new NotFoundException(History.class, id));
        historyRepository.delete(history);
    }

    @Override
    public void removeAll(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException(Member.class, memberId));
        historyRepository.deleteAllByMember(member);
    }
}
