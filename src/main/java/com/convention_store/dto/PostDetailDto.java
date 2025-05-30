package com.convention_store.dto;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.convention_store.domain.Post;
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

    public static PostDetailDto from(Post post) {
        return PostDetailDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .authorName(post.getAuthorName())
                .createdAt(post.getCreatedAt())
                .likeCount(post.getLikeCount() != null ? post.getLikeCount() : 0L)
                .comments(
                        post.getComments() != null ?
                                post.getComments().stream()
                                        .map(CommentDto::from)
                                        .collect(Collectors.toList())
                                : Collections.emptyList()
                )
                .franchiseName(
                        post.getFranchise() != null ? post.getFranchise().getName() : null
                )
                .combination(
                        post.getCombination() != null ? CombinationDto.from(post.getCombination()) : null
                )
                .build();
    }
}
