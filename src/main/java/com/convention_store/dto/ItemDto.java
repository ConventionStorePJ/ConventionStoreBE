package com.convention_store.dto;

import com.convention_store.domain.enums.DiscountType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemDto {
    private Long itemId;
    private Long franchiseId;
    private String itemName;
    private DiscountType discountType;
    private String imageUrl;
    private Long price;
}
