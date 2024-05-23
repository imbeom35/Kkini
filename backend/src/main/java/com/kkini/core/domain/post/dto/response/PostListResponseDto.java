package com.kkini.core.domain.post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@Schema(description = "포스트 조회 응답 DTO")
public class PostListResponseDto {

    @Schema(description = "포스트 ID")
    private Long id;

    @Schema(description = "내용")
    private String contents;

    @Schema(description = "생성일")
    private Timestamp createDateTime;

    @Schema(description = "좋아요 수")
    private int likeCnt;

    @Schema(description = "싫어요 수")
    private int disLikeCnt;

    @Schema(description = "평균 가격")
    private int avgPrice;

    @Schema(description = "나의 가격")
    private int myPrice;

    @Schema(description = "내가 쓴 글")
    private Boolean isMine;

    @Schema(description = "사용자 ID")
    private Long memberId;

    @Schema(description = "사용자 이름")
    private String nickname;

    @Schema(description = "사용자 이미지")
    private String memberImage;

    @Schema(description = "레시피 ID")
    private Long recipeId;

    @Schema(description = "레시피 이름")
    private String recipeName;

    @Schema(description = "반응(좋아요: true, 싫어요: false, 없음: null)")
    private Boolean reaction;

    @Schema(description = "스크랩 여부")
    private Boolean isScrap;

    @Schema(description = "이미지 URL")
    private List<String> imageList;

    @Schema(description = "이미지 아이디")
    private List<Long> imageIndexList;

    @Schema(description = "댓글 수")
    private int commentCnt;

    public PostListResponseDto(Long id, String contents, Timestamp createDateTime, int likeCnt, int hateCnt, int avgPrice, int myPrice, Boolean isMine, Long memberId, String nickname, String memberImage, Long recipeId, String recipeName, Boolean reaction, Boolean isScrap) {
        this.id = id;
        this.contents = contents;
        this.createDateTime = createDateTime;
        this.likeCnt = likeCnt;
        this.disLikeCnt = hateCnt;
        this.avgPrice = avgPrice;
        this.myPrice = myPrice;
        this.isMine = isMine;
        this.memberId = memberId;
        this.nickname = nickname;
        this.memberImage = memberImage;
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.reaction = reaction;
        this.isScrap = isScrap;
    }

}
