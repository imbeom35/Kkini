package com.kkini.core.domain.member.service;

import com.kkini.core.domain.member.dto.SearchMemberResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberQueryService {

    Page<SearchMemberResponseDto> searchMember(String query, Pageable pageable);
}
