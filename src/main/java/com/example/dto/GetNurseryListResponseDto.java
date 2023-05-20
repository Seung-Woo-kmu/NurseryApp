package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetNurseryListResponseDto {
    private List<NurseryDto> nurseryList;
}