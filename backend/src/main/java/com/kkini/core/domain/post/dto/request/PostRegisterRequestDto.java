package com.kkini.core.domain.post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "포스트 생성 요청 DTO")
public class PostRegisterRequestDto {

    @Schema(description = "내용")
    private String contents;

    @Schema(description = "레시피")
    private Long recipeId;

}
