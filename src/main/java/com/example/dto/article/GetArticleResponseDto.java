package com.example.dto.article;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetArticleResponseDto {
    private ArticleDto article;
}