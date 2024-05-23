package com.kkini.core.domain.reaction.controller;

import com.kkini.core.domain.oauth2.UserPrincipal;
import com.kkini.core.domain.reaction.dto.request.ReactionRegisterRequestDto;
import com.kkini.core.domain.reaction.service.ReactionService;
import com.kkini.core.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.kkini.core.global.response.Response.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reaction")
@Slf4j
@Tag(name = "Reaction", description = "Reaction 관리 API")
public class ReactionController {

    private final ReactionService reactionService;

    @Operation(summary = "포스트 반응", description = "포스트에 반응을 한다.")
    @Parameters({
            @Parameter(name = "id", description = "포스트 식별자"),
            @Parameter(name = "state", description = "상태(좋아요:true/싫어요:false/없음:null)")
    })
    @PostMapping
    public Response<Boolean> modifyReaction(
            @RequestBody ReactionRegisterRequestDto reactionRegisterRequestDto,
            @Parameter(hidden = true) @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return OK(reactionService.saveReaction(reactionRegisterRequestDto, userPrincipal.getId()));
    }

}
