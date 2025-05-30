package com.convention_store.domain;

import com.convention_store.domain.enums.DiscountType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "discount")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id", callSuper = false)
public class Discount extends BaseTimeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type", nullable = false)
    private DiscountType discountType;
    
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;
    
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;
    
    @Builder
    public Discount(DiscountType discountType, LocalDateTime startDate, LocalDateTime endDate, Item item) {
        if (discountType == null || startDate == null || endDate == null) {
            throw new IllegalArgumentException("Discount type, start date, and end date cannot be null.");
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }
        this.discountType = discountType;
        this.startDate = startDate;
        this.endDate = endDate;
        if (item != null) {
            associateWithItem(item);
        }
    }
    
    // --- 비즈니스 로직 (관계 설정) ---
    public void associateWithItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null for Discount.");
        }

        // 기존에 연결된 아이템이 있다면 해제
        if (this.item != null && this.item.getDiscount() == this) {
            this.item.setDiscount(null);
        }

        this.item = item;

        // item에도 Discount 설정 (양방향 동기화)
        if (item.getDiscount() != this) {
            item.setDiscount(this);
        }
    }



    // 관계 제거 (nullable = false 이므로 일반적으로는 사용하지 않음, 예시로만)
    public void disassociateItem() {
        if (this.item != null) {
            if (this.item.getDiscount() == this) {
                this.item.setDiscount(null);
            }
            this.item = null;
        }
    }


    // --- 비즈니스 로직 (필드 값 변경) ---
    public void updateDiscountPeriod(LocalDateTime newStartDate, LocalDateTime newEndDate) {
        if (newStartDate == null || newEndDate == null) {
            throw new IllegalArgumentException("Start date and end date cannot be null.");
        }
        if (newStartDate.isAfter(newEndDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }
        this.startDate = newStartDate;
        this.endDate = newEndDate;
    }
    
    public void changeDiscountType(DiscountType newType) {
        if (newType == null) {
            throw new IllegalArgumentException("Discount type cannot be null.");
        }
        this.discountType = newType;
    }
}