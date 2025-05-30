package com.convention_store.dto;

import com.convention_store.domain.Combination;
import com.convention_store.domain.enums.CombinationCategoryType;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CombinationDto {
    private Long combinationId;
    private String title;
    private CombinationCategoryType tag;
    private Long franchiseId;
    private Long likeCount;
    private List<ItemDto> itemList;
    
    public static CombinationDto from(Combination combination) {
        return CombinationDto.builder()
            .combinationId(combination.getId())
            .title(combination.getTitle())
            .tag(combination.getCategory())
            .franchiseId(combination.getFranchise().getId())
            .likeCount(combination.getLikeCount())
            .itemList(combination.getCombinationItems().stream()
                .map(combinationItem -> combinationItem.getItem())
                .map(ItemDto::from)
                .toList())
            .build();
    }
}
