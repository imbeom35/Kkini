package com.kkini.core.domain.mypage.service;

import com.kkini.core.domain.member.entity.Member;
import com.kkini.core.domain.mypage.dto.response.MypageInfoResponseListDto;

import java.util.List;

public interface MypageQueryService {

    MypageInfoResponseListDto getMypageInfo(Long memberId);
    String getProfileImage(Long memberId);
}
