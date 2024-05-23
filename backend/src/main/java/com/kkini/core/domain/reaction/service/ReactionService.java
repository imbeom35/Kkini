package com.kkini.core.domain.reaction.service;

import com.kkini.core.domain.reaction.dto.request.ReactionRegisterRequestDto;

public interface ReactionService {

    Boolean saveReaction(ReactionRegisterRequestDto reactionRegisterRequestDto, Long memberId);

}
