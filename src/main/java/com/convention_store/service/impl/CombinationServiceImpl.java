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
import jakarta.persistence.EntityNotFoundException;
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
        Combination combination = combinationRepository.findById(combinationId)
            .orElseThrow(() -> new EntityNotFoundException("Combination not found"));
        
        return CombinationDto.from(combination);
    }
    
    // TODO: 문서화 - 엔티티 빌더로 깔끔하게 문제해결하기
    @Override
    @Transactional
    public CombinationDto createCombination(CombinationCreateDto combinationCreateDto) {
        Franchise franchise = franchiseRepository.findById(combinationCreateDto.getFranchiseId())
            .orElseThrow(() -> new IllegalArgumentException("Franchise not found"));
        
        Combination combination = Combination.builder()
                .title(combinationCreateDto.getTitle())
                .franchise(franchise)
                .description(combinationCreateDto.getDescription())
                .category(combinationCreateDto.getTag())
                .build();
        combinationRepository.save(combination);
        
        createCombinationItem(combination, combinationCreateDto.getItemIdList(), franchise);
        
        return CombinationDto.from(combinationRepository.save(combination));
    }
    
    @Transactional
    protected void createCombinationItem(
        Combination combination,
        List<Long> itemIdList,
        Franchise franchise
    ) {
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
