package com.convention_store.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id", callSuper = false)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "author_name", length = 50)
    private String authorName;

    @Column(name = "content", length = 300, nullable = false)
    private String content;

    @Column(name = "password_hash", length = 260)
    private String passwordHash;

    @Builder
    public Comment(Post post, String authorName, String content, String passwordHash) {
        if (post == null || content == null || content.isBlank()) {
            throw new IllegalArgumentException("Post and content cannot be null.");
        }
        this.post = post;
        this.authorName = authorName;
        this.content = content;
        this.passwordHash = passwordHash;
    }

    public void update(String newContent, String newAuthorName, String newPasswordHash) {
        if (newContent == null || newContent.isBlank()) {
            throw new IllegalArgumentException("Content cannot be null or blank.");
        }
        this.content = newContent;
        this.authorName = newAuthorName;
        this.passwordHash = newPasswordHash;
    }

    public void associateWithPost(Post post) {
        this.post = post;
        if (!post.getComments().contains(this)) {
            post.getComments().add(this);
        }
    }

    public void disassociatePost() {
        if (this.post != null) {
            this.post.getComments().remove(this);
            this.post = null;
        }
    }
}
