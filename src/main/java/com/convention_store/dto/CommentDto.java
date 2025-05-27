package com.convention_store.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentDto {
    private Long commentId;
    private String authorName;
    private String content;
    private LocalDateTime createdAt;
}
