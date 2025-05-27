package com.convention_store.dto;

import java.time.LocalDateTime;

public class PostDto {
    private Long postId;
    private String title;
    private String content;
    private String authorName;
    private LocalDateTime createdAt;
    private Long likeCount;
    private Long commentCount;
    private String franchiseName;
    private CombinationDto combination;
}
