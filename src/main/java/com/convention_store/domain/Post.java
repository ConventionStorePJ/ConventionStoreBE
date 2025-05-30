package com.convention_store.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id", callSuper = false)
public class Post extends BaseTimeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    
    @Column(name = "title", length = 100, nullable = false)
    private String title;
    
    @Column(name = "content", length = 2048, nullable = false)
    private String content;
    
    @Column(name = "author_name", length = 50)
    private String authorName;
    
    @Column(name = "password_hash", length = 260)
    private String passwordHash;

    @Column(name = "like_count")
    private Long likeCount;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "combination_id", nullable = true)
    private Combination combination;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id", nullable = true)
    private Franchise franchise;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();


    public void updateLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    @Builder
    public Post(String title, String content, String authorName, Long likeCount, String passwordHash, Combination combination, Franchise franchise) {
        if (title == null || title.isBlank() || content == null || content.isBlank()) {
            throw new IllegalArgumentException("Post title and content cannot be null or blank.");
        }
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.authorName = authorName;
        this.passwordHash = passwordHash;
        // 빌더를 통해 생성 시에도 연관관계 동기화
        if (combination != null) {
            associateWithCombination(combination);
        }
        if (franchise != null) {
            associateWithFranchise(franchise);
        }
    }
    
    // --- 비즈니스 로직 (관계 설정) ---
    public void associateWithCombination(Combination combination) {
        // 기존 관계 제거
        if (this.combination != null) {
            this.combination.getPosts().remove(this);
        }
        this.combination = combination;
        // 새로운 관계 설정
        if (combination != null && !combination.getPosts().contains(this)) {
            combination.getPosts().add(this);
        }
    }
    
    public void associateWithFranchise(Franchise franchise) {
        // 기존 관계 제거
        if (this.franchise != null) {
            this.franchise.getPosts().remove(this);
        }
        this.franchise = franchise;
        // 새로운 관계 설정
        if (franchise != null && !franchise.getPosts().contains(this)) {
            franchise.getPosts().add(this);
        }
    }
    
    // --- 비즈니스 로직 (관계 제거) ---
    public void disassociateCombination() {
        if (this.combination != null) {
            this.combination.getPosts().remove(this);
            this.combination = null;
        }
    }
    
    public void disassociateFranchise() {
        if (this.franchise != null) {
            this.franchise.getPosts().remove(this);
            this.franchise = null;
        }
    }
    
    // --- 비즈니스 로직 (필드 값 변경) ---
    public void updatePost(String newTitle, String newContent, String newAuthorName, String newPasswordHash) {
        if (newTitle == null || newTitle.isBlank() || newContent == null || newContent.isBlank()) {
            throw new IllegalArgumentException("Post title and content cannot be null or blank.");
        }
        this.title = newTitle;
        this.content = newContent;
        this.authorName = newAuthorName;
        this.passwordHash = newPasswordHash;
    }
}