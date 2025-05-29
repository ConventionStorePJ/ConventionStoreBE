package com.convention_store.service.impl;

import com.convention_store.domain.enums.DiscountType;
import com.convention_store.dto.ItemDto;
import com.convention_store.repository.ItemRepository;
import com.convention_store.service.ItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    
    private final ItemRepository itemRepository;
    
    @Override
    public List<ItemDto> searchItems(String keyword, Long franchiseId, DiscountType discountType) {
        return itemRepository.findByItemNameAndFranchiseIdAndDiscountType(keyword, franchiseId, discountType)
            .stream()
            .map(ItemDto::from)
            .toList();
    }
}
