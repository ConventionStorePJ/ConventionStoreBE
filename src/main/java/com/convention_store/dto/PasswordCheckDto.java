package com.convention_store.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class PasswordCheckDto {
    @Schema(description = "비밀번호 해시", example = "hash1234")
    private String passwordHash;
}
