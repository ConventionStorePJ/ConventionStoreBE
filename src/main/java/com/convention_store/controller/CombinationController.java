package com.convention_store.controller;

import com.convention_store.domain.enums.CombinationCategoryType;
import com.convention_store.domain.enums.SortType;
import com.convention_store.dto.CombinationDto;
import com.convention_store.dto.request.CombinationCreateDto;
import com.convention_store.service.CombinationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/combos")
@RequiredArgsConstructor
public class CombinationController {
    
    private final CombinationService combinationService;
    
    @GetMapping("")
    public ResponseEntity<List<CombinationDto>> getCombinationList(
        @RequestParam(name = "tagName", required = false) CombinationCategoryType tagName,
        @RequestParam(name = "franchiseId", required = false) Long franchiseId,
        @RequestParam(name = "sort", required = false) SortType sortType
    ) {
        if (sortType == null) sortType = SortType.RECENT;
        return ResponseEntity.ok(combinationService.getCombinationList(
            tagName,
            franchiseId,
            sortType
        ));
    }
    
    @GetMapping("/{combinationId}")
    public ResponseEntity<CombinationDto> getCombination(@PathVariable Long combinationId) {
        return ResponseEntity.ok(combinationService.getCombination(combinationId));
    }
    
    @GetMapping("/tags")
    public ResponseEntity<List<CombinationCategoryType>> getCombinationTags() {
        return ResponseEntity.ok(combinationService.getCombinationCategories());
    }

    @PostMapping("")
    public ResponseEntity<CombinationDto> createCombination(@RequestBody CombinationCreateDto combinationCreateDto) {
        
        return ResponseEntity.ok(combinationService.createCombination(combinationCreateDto));
    }
}
