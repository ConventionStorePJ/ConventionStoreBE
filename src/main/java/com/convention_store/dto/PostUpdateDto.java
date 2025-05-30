package com.convention_store.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "게시글 수정 DTO")
public class PostUpdateDto {
    @Schema(description = "수정할 제목", example = "수정된 제목")
    private String title;

    @Schema(description = "수정할 내용", example = "수정된 내용")
    private String content;

    @Schema(description = "비밀번호 해시", example = "hash1234")
    private String passwordHash;
}
