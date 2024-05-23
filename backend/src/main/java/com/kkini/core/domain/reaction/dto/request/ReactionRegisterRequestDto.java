package com.kkini.core.domain.reaction.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "반응 생성 요청 DTO")
public class ReactionRegisterRequestDto {

    @Schema(description = "포스트 ID")
    private Long postId;

    @Schema(description = "반응")
    private Boolean state;

}
