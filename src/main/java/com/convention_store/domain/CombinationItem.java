package com.convention_store.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "combination_item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id", callSuper = false)
public class CombinationItem extends BaseTimeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "combination_item_id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "combination_id", nullable = false)
    private Combination combination;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id", nullable = false)
    private Franchise franchise;
    
    @Builder
    public CombinationItem(Item item, Combination combination, Franchise franchise) {
        if (item == null || combination == null || franchise == null) {
            throw new IllegalArgumentException("Item, Combination, and Franchise cannot be null for CombinationItem.");
        }
        // 빌더를 통해 생성 시에도 연관관계 동기화
        associateWithItem(item);
        associateWithCombination(combination);
        associateWithFranchise(franchise);
    }
    
    // --- 비즈니스 로직 (관계 설정) ---
    public void associateWithItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null for CombinationItem.");
        }
        if (this.item != null) {
            this.item.getCombinationItems().remove(this);
        }
        this.item = item;
        if (!item.getCombinationItems().contains(this)) {
            item.getCombinationItems().add(this);
        }
    }
    
    public void associateWithCombination(Combination combination) {
        if (combination == null) {
            throw new IllegalArgumentException("Combination cannot be null for CombinationItem.");
        }
        if (this.combination != null) {
            this.combination.getCombinationItems().remove(this);
        }
        this.combination = combination;
        if (!combination.getCombinationItems().contains(this)) {
            combination.getCombinationItems().add(this);
        }
    }
    
    public void associateWithFranchise(Franchise franchise) {
        if (franchise == null) {
            throw new IllegalArgumentException("Franchise cannot be null for CombinationItem.");
        }
        if (this.franchise != null) {
            this.franchise.getCombinationItems().remove(this);
        }
        this.franchise = franchise;
        if (!franchise.getCombinationItems().contains(this)) {
            franchise.getCombinationItems().add(this);
        }
    }
    
    // --- 비즈니스 로직 (관계 제거) ---
    public void disassociateItem() {
        if (this.item != null) {
            this.item.getCombinationItems().remove(this);
            this.item = null;
        }
    }
    
    public void disassociateCombination() {
        if (this.combination != null) {
            this.combination.getCombinationItems().remove(this);
            this.combination = null;
        }
    }
    
    public void disassociateFranchise() {
        if (this.franchise != null) {
            this.franchise.getCombinationItems().remove(this);
            this.franchise = null;
        }
    }
}