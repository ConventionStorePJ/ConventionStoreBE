package com.convention_store.service.impl;

import com.convention_store.domain.Combination;
import com.convention_store.domain.CombinationItem;
import com.convention_store.domain.Franchise;
import com.convention_store.domain.Item;
import com.convention_store.domain.enums.CombinationCategoryType;
import com.convention_store.domain.enums.SortType;
import com.convention_store.dto.CombinationDto;
import com.convention_store.dto.request.CombinationCreateDto;
import com.convention_store.repository.CombinationItemRepository;
import com.convention_store.repository.CombinationRepository;
import com.convention_store.repository.FranchiseRepository;
import com.convention_store.repository.ItemRepository;
import com.convention_store.service.CombinationService;
import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CombinationServiceImpl implements CombinationService {
    
    private final CombinationRepository combinationRepository;
    private final ItemRepository itemRepository;
    private final CombinationItemRepository combinationItemRepository;
    private final FranchiseRepository franchiseRepository;
    
    @Override
    public List<CombinationCategoryType> getCombinationCategories() {
        return Arrays.stream(CombinationCategoryType.values()).toList();
    }
    
    @Override
    public List<CombinationDto> getCombinationList(
        CombinationCategoryType category,
        Long franchiseId,
        SortType sortType
    ) {
        List<Combination> result = switch (sortType) {
            case RECENT ->
                combinationRepository.findByCategoryAndFranchiseIdOrderByCreateAtDesc(franchiseId, category);
            case POPULAR ->
                combinationRepository.findByCategoryAndFranchiseIdOrderByLikeCountDesc(franchiseId, category);
            default -> throw new IllegalStateException("Unexpected value: " + sortType);
        };
        
        return result.stream().map(CombinationDto::from).toList();
    }
    
    @Override
    public CombinationDto getCombination(Long combinationId) {
        Combination combination = combinationRepository.findById(combinationId).orElseThrow(() -> new IllegalArgumentException("Combination not found"));
        return CombinationDto.from(combination);
    }
    
    @Override
    @Transactional
    public CombinationDto createCombination(CombinationCreateDto combinationCreateDto) {
        Combination combination = new Combination(combinationCreateDto);
        createCombinationItem(combination, combinationCreateDto.getItemIdList(), combinationCreateDto.getFranchiseId());
        
        combinationRepository.save(combination);
        return CombinationDto.from(combinationRepository.save(combination));
    }
    
    @Transactional
    protected void createCombinationItem(
        Combination combination,
        List<Long> itemIdList,
        Long franchiseId
    ) {
        Franchise franchise = franchiseRepository.findById(franchiseId).orElseThrow(() -> new IllegalArgumentException("Franchise not found"));
        itemIdList.forEach(id -> {
            Item item = itemRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item does not exists"));
            
            combinationItemRepository.save(CombinationItem.builder()
                .combination(combination)
                .franchise(franchise)
                .item(item)
                .build());
            
            itemRepository.save(item);
        });
        
        franchiseRepository.save(franchise);
    }
}
