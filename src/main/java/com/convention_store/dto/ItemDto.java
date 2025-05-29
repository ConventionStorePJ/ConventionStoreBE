package com.convention_store.dto;

import com.convention_store.domain.Item;
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
    
    public static ItemDto from(Item item) {
        return ItemDto.builder()
            .itemId(item.getId())
            .franchiseId(item.getFranchise().getId())
            .itemName(item.getItemName())
            .discountType(item.getDiscountType())
            .imageUrl(item.getImageUrl())
            .price(item.getPrice())
            .build();
    }
}
