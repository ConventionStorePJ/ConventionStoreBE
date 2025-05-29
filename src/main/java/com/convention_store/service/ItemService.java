package com.convention_store.service;

import com.convention_store.domain.enums.DiscountType;
import com.convention_store.dto.ItemDto;
import java.util.List;

public interface ItemService {
    
    List<ItemDto> searchItems(String keyword, Long franchiseId, DiscountType discountType);
}
