package com.convention_store.dto.request;

import com.convention_store.domain.enums.CombinationCategoryType;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CombinationCreateDto {
    private String name;
    private String description;
    private CombinationCategoryType tag;
    private Long franchiseId;
    private List<Long> itemIdList;
}
