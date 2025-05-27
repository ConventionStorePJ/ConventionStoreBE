package com.convention_store.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FranchiseDto {
    private Long franchiseId;
    private String franchiseName;
}
