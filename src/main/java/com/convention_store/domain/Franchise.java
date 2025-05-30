package com.convention_store.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "franchise")
@Getter // Getter는 유지 (필드 값 읽기는 허용)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id", callSuper = false)
public class Franchise extends BaseTimeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "franchise_id")
    private Long id;
    
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    
    @OneToMany(mappedBy = "franchise", fetch = FetchType.LAZY)
    private List<Item> items = new ArrayList<>();
    
    @OneToMany(mappedBy = "franchise", fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();
    
    @OneToMany(mappedBy = "franchise", fetch = FetchType.LAZY)
    private List<CombinationItem> combinationItems = new ArrayList<>();
    
    @Builder // 빌더를 통해 생성 시 값 초기화
    public Franchise(String name) {
        this.name = name;
    }
    
    // --- 비즈니스 로직 (관계 추가) ---
    // Item과의 관계를 설정하는 메서드
    public void addItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }
        if (!this.items.contains(item)) { // 중복 방지
            this.items.add(item);
            // Item 엔티티의 associateWithFranchise 메서드를 호출하여 양방향 관계 동기화
            item.associateWithFranchise(this);
        }
    }
    
    // Post와의 관계를 설정하는 메서드
    public void addPost(Post post) {
        if (post == null) {
            throw new IllegalArgumentException("Post cannot be null.");
        }
        if (!this.posts.contains(post)) { // 중복 방지
            this.posts.add(post);
            // Post 엔티티의 associateWithFranchise 메서드를 호출하여 양방향 관계 동기화
            post.associateWithFranchise(this);
        }
    }
    
    // CombinationItem 과의 관계를 설정하는 메서드
    public void addCombinationItem(CombinationItem combinationItem) {
        if (combinationItem == null) {
            throw new IllegalArgumentException("CombinationItem cannot be null.");
        }
        if (!this.combinationItems.contains(combinationItem)) { // 중복 방지
            this.combinationItems.add(combinationItem);
            // CombinationItem 엔티티의 associateWithFranchise 메서드를 호출하여 양방향 관계 동기화
            combinationItem.associateWithFranchise(this);
        }
    }
    
    // --- 비즈니스 로직 (관계 제거) ---
    public void removeItem(Item item) {
        if (this.items.remove(item)) {
            item.disassociateFranchise(); // Item 에서 관계 해제 메서드 호출
        }
    }
    
    public void removePost(Post post) {
        if (this.posts.remove(post)) {
            post.disassociateFranchise(); // Post 에서 관계 해제 메서드 호출
        }
    }
    
    public void removeCombinationItem(CombinationItem combinationItem) {
        if (this.combinationItems.remove(combinationItem)) {
            combinationItem.disassociateFranchise(); // CombinationItem 에서 관계 해제 메서드 호출
        }
    }
    
    // --- 비즈니스 로직 (필드 값 변경) ---
    public void changeName(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("Franchise name cannot be null or blank.");
        }
        this.name = newName;
    }
}