package com.convention_store.dto;


import com.convention_store.domain.Combination;
import lombok.Builder;
import lombok.Getter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class CombinationDto {
    private Long combinationId;
    private String title;
    private Long franchiseId;
    private Long likeCount;
    private List<ItemDto> itemList;

    public static CombinationDto from(Combination combination) {
        return CombinationDto.builder()
                .combinationId(combination.getId())
                .title(combination.getTitle())
                // Combination → CombinationItem → Franchise
                .franchiseId(
                        combination.getCombinationItems().isEmpty()
                        ? null
                        : combination.getCombinationItems().get(0).getFranchise().getId()
                )
                .likeCount(0L)
                // Combination → CombinationItem → Item
                .itemList(
                        combination.getCombinationItems().stream()
                                .map(ci -> ItemDto.from(ci.getItem()))
                                .collect(Collectors.toList())
                )
                .build();
    }
}



