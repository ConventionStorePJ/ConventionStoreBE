package com.convention_store.controller;

import com.convention_store.domain.enums.DiscountType;
import com.convention_store.dto.ItemDto;
import com.convention_store.service.ItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {
    
    private final ItemService itemService;
    
    @GetMapping("")
    public ResponseEntity<List<ItemDto>> getItemList(
        @RequestParam(name = "searchKeyword", required = false) String keyword,
        @RequestParam(name = "franchiseId", required = false) Long franchiseId,
        @RequestParam(name = "discountType", required = false) DiscountType discountType
    ) {
        return ResponseEntity.ok(itemService.searchItems(keyword, franchiseId, discountType));
    }
}
