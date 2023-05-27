package com.example.dto.heart;

import lombok.Data;

@Data
public class CreateHeartResponseDto {
    private Long id;

    public CreateHeartResponseDto(Long id) {
        this.id = id;
    }
}
