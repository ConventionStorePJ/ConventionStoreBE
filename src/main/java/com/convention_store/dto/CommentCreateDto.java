package com.convention_store.dto;

import com.convention_store.domain.Comment;
import com.convention_store.domain.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "댓글 등록 요청 DTO")
public class CommentCreateDto {

    @Schema(description = "작성자 이름", example = "댓글장인")
    private String authorName;

    @Schema(description = "댓글 내용", example = "이 조합 정말 맛있어 보이네요!")
    private String content;

    @Schema(description = "비밀번호 해시", example = "hashed1234")
    private String passwordHash;

    public Comment toEntity(Post post) {
        return Comment.builder()
                .post(post)
                .authorName(authorName)
                .content(content)
                .passwordHash(passwordHash)
                .build();
    }
}