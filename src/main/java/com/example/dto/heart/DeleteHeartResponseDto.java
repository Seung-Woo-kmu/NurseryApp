package com.example.dto.heart;

import lombok.Data;

@Data
public class DeleteHeartResponseDto {
    private Long deleteHeartId;

    public DeleteHeartResponseDto(Long deleteHeartId) {
        this.deleteHeartId = deleteHeartId;
    }
}
