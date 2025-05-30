package com.convention_store.domain;

import com.convention_store.domain.enums.DiscountType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id", callSuper = false)
public class Item extends BaseTimeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;
    
    @Column(name = "item_name", length = 100, nullable = false)
    private String itemName;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id", nullable = false)
    private Franchise franchise;

    @Column(name = "price")
    private Long price;

    @Column(name = "image_url")
    private String imageUrl;
    
    // 복수할인이 들어가는 경우는 거의 없으므로 onetoone으로 변경
    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private Discount discount;
    
    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private final List<CombinationItem> combinationItems = new ArrayList<>();
    
    @Builder
    public Item(String itemName, Franchise franchise) {
        if (itemName == null || itemName.isBlank()) {
            throw new IllegalArgumentException("Item name cannot be null or blank.");
        }
        this.itemName = itemName;
        // 빌더를 통해 생성 시에도 연관관계 동기화
        if (franchise != null) {
            associateWithFranchise(franchise);
        }
    }
    
    // --- 비즈니스 로직 (관계 설정) ---
    // ManyToOne 관계의 소유자이므로, 이 메서드에서 관계를 설정
    public void associateWithFranchise(Franchise franchise) {
        if (franchise == null) {
            throw new IllegalArgumentException("Franchise cannot be null for Item.");
        }
        // 기존 관계 제거 (필요 시)
        if (this.franchise != null) {
            this.franchise.getItems().remove(this); // 기존 프랜차이즈에서 현재 아이템 제거
        }
        this.franchise = franchise;
        if (!franchise.getItems().contains(this)) {
            franchise.getItems().add(this); // 새로운 프랜차이즈에 현재 아이템 추가
        }
    }
    
    // 관계 제거 (nullable = false 이므로 일반적으로는 사용하지 않음, 예시로만)
    public void disassociateFranchise() {
        if (this.franchise != null) {
            this.franchise.getItems().remove(this);
            this.franchise = null; // 실제 DB에서는 NOT NULL 제약조건 위반
        }
    }

    public void setDiscount(Discount discount) {
        if (discount == null) throw new IllegalArgumentException("Discount cannot be null.");

        // 기존 할인 제거 (있다면)
        if (this.discount != null) {
            this.discount.disassociateItem(); // Discount에 이 메서드 있어야 함
        }

        this.discount = discount;
        discount.associateWithItem(this); // 양방향 관계 설정
    }

    public void addCombinationItem(CombinationItem combinationItem) {
        if (combinationItem == null) {
            throw new IllegalArgumentException("CombinationItem cannot be null.");
        }
        if (!this.combinationItems.contains(combinationItem)) {
            this.combinationItems.add(combinationItem);
            combinationItem.associateWithItem(this);
        }
    }
    
    // --- 비즈니스 로직 (관계 제거) ---
    public void removeDiscount() {
        if (this.discount != null) {
            this.discount.disassociateItem();
            this.discount = null;
        }
    }

    public void removeCombinationItem(CombinationItem combinationItem) {
        if (this.combinationItems.remove(combinationItem)) {
            combinationItem.disassociateItem();
        }
    }
    
    // --- 비즈니스 로직 (필드 값 변경) ---
    public void changeItemName(String newItemName) {
        if (newItemName == null || newItemName.isBlank()) {
            throw new IllegalArgumentException("Item name cannot be null or blank.");
        }
        this.itemName = newItemName;
    }
  
}