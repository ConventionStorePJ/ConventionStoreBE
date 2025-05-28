package com.convention_store.dto;

import java.time.LocalDateTime;

import com.convention_store.domain.Comment;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentDto {
    private Long commentId;
    private String authorName;
    private String content;
    private LocalDateTime createdAt;

    public static CommentDto from(Comment comment) {
        return CommentDto.builder()
                .commentId(comment.getId())
                .authorName(comment.getAuthorName())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
