package com.example.dto.article;

import lombok.Data;

@Data
public class CreateArticleResponseDto {
    private ArticleDto articleDto;

    public CreateArticleResponseDto(ArticleDto articleDto) {
        this.articleDto = articleDto;
    }
}
