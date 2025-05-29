package com.convention_store.dto;

import com.convention_store.domain.Franchise;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FranchiseDto {
    private Long franchiseId;
    private String franchiseName;
    
    public static FranchiseDto from(Franchise franchise) {
        return FranchiseDto.builder().franchiseId(franchise.getId()).franchiseName(franchise.getName()).build();
    }
}
