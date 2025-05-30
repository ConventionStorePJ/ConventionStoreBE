package com.convention_store.service;

import com.convention_store.domain.enums.CombinationCategoryType;
import com.convention_store.domain.enums.SortType;
import com.convention_store.dto.CombinationDto;
import com.convention_store.dto.request.CombinationCreateDto;
import java.util.List;

public interface CombinationService {
    List<CombinationCategoryType> getCombinationCategories();
    
    List<CombinationDto> getCombinationList(CombinationCategoryType category, Long franchiseId, SortType sortType);
    
    CombinationDto getCombination(Long combinationId);
    
    CombinationDto createCombination(CombinationCreateDto combinationDto);
}
