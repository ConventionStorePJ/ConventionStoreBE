package com.convention_store.dto;

import java.time.LocalDateTime;

import com.convention_store.domain.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
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

    public static PostDto from(Post post) {
        return PostDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .authorName(post.getAuthorName())
                .createdAt(post.getCreatedAt())
                .likeCount(post.getLikeCount() != null ? post.getLikeCount() : 0L)
                .commentCount((long) post.getComments().size()) // 아직 댓글 기능 미구현이면 기본값
                .franchiseName(
                        post.getFranchise() != null ? post.getFranchise().getName() : null
                )
                .combination(
                        post.getCombination() != null ? CombinationDto.from(post.getCombination()) : null
                )
                .build();
    }
}


