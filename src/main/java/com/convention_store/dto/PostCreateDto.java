package com.convention_store.dto;

import com.convention_store.domain.Combination;
import com.convention_store.domain.Franchise;
import com.convention_store.domain.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "게시글 생성 요청 DTO")
public class PostCreateDto {
    @Schema(description = "게시글 제목", example = "라면 꿀조합 공유해요")
    private String title;

    @Schema(description = "게시글 내용", example = "진라면 + 스팸 진짜 맛있음")
    private String content;

    @Schema(description = "작성자 이름", example = "푸드킹")
    private String authorName;

    @Schema(description = "비밀번호 해시", example = "hash1234")
    private String passwordHash;

    @Schema(description = "프랜차이즈 ID", example = "1")
    private Long franchiseId;

    @Schema(description = "꿀조합 ID (nullable)", example = "3")
    private Long combinationId;

    // Post Entity로 변환
    public Post toEntity(Franchise franchise, Combination combination) {
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .authorName(this.authorName)
                .passwordHash(this.passwordHash)
                .franchise(franchise)
                .combination(combination)
                .build();
    }
}
