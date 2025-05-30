package com.convention_store.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikeDto {
    private String message;
    private long likeCount;
}
