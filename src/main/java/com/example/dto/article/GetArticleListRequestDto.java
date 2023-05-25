package com.example.dto.article;

import com.example.enums.BoardType;
import com.example.enums.SortType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetArticleListRequestDto {
    private BoardType boardType;
    private SortType sortType;
    private String cursor;
}