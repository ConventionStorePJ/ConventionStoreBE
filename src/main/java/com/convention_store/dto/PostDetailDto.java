package com.convention_store.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostDetailDto {
    private Long postId;
    private String title;
    private String content;
    private String authorName;
    private LocalDateTime createdAt;
    private Long likeCount;
    private List<CommentDto> comments;
    private String franchiseName;
    private CombinationDto combination;
}
