package com.convention_store.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CombinationDto {
    private Long combinationId;
    private String title;
    private Long franchiseId;
    private Long likeCount;
    private List<ItemDto> itemList;
}
