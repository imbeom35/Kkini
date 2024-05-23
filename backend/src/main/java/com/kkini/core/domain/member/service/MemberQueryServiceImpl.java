package com.kkini.core.domain.member.service;

import com.kkini.core.domain.member.dto.SearchMemberResponseDto;
import com.kkini.core.domain.member.repository.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberQueryRepository memberQueryRepository;
    @Override
    public Page<SearchMemberResponseDto> searchMember(String query, Pageable pageable) {
        return memberQueryRepository.searchMember(query, pageable);
    }
}
