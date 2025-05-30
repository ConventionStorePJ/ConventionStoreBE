package com.convention_store.domain;

import com.convention_store.domain.enums.CombinationCategoryType;
import com.convention_store.dto.request.CombinationCreateDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "combination")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id", callSuper = false)
public class Combination extends BaseTimeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "combination_id")
    private Long id;
    
    @Column(name = "title", length = 50, nullable = false)
    private String title;
    
    @Column
    @Enumerated(EnumType.STRING)
    private CombinationCategoryType category;
    
    @Column(name = "description", length = 255)
    private String description;
    
    @OneToMany(mappedBy = "combination", fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;
    
    @Column(name = "like_count")
    private Long likeCount;
    
    @OneToMany(mappedBy = "combination", fetch = FetchType.LAZY)
    private List<CombinationItem> combinationItems = new ArrayList<>();
    
    @Builder
    public Combination(String title, String description, CombinationCategoryType category, Franchise franchise) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Combination title cannot be null or blank.");
        }
        if (category == null) {
            throw new IllegalArgumentException("Combination category cannot be null or blank.");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Combination description cannot be null or blank.");
        }
        if (franchise == null) {
            throw new IllegalArgumentException("Combination franchise cannot be null or blank.");
        }
        
        this.title = title;
        this.description = description;
        this.category = category;
        this.likeCount = 0L;
        this.posts = new ArrayList<>();
        this.franchise = franchise;
        this.combinationItems = new ArrayList<>();
    }
    
    // --- 비즈니스 로직 (관계 추가) ---
    public void addPost(Post post) {
        if (post == null) {
            throw new IllegalArgumentException("Post cannot be null.");
        }
        if (!this.posts.contains(post)) {
            this.posts.add(post);
            post.associateWithCombination(this);
        }
    }
    
    public void addCombinationItem(CombinationItem combinationItem) {
        if (combinationItem == null) {
            throw new IllegalArgumentException("CombinationItem cannot be null.");
        }
        if (!this.combinationItems.contains(combinationItem)) {
            this.combinationItems.add(combinationItem);
            combinationItem.associateWithCombination(this);
        }
    }
    
    // --- 비즈니스 로직 (관계 제거) ---
    public void removePost(Post post) {
        if (this.posts.remove(post)) {
            post.disassociateCombination();
        }
    }
    
    public void removeCombinationItem(CombinationItem combinationItem) {
        if (this.combinationItems.remove(combinationItem)) {
            combinationItem.disassociateCombination();
        }
    }
    
    // --- 비즈니스 로직 (필드 값 변경) ---
    public void changeTitle(String newTitle) {
        if (newTitle == null || newTitle.isBlank()) {
            throw new IllegalArgumentException("Combination title cannot be null or blank.");
        }
        this.title = newTitle;
    }
    
    public void changeDescription(String newDescription) {
        this.description = newDescription; // description은 null 허용
    }
}