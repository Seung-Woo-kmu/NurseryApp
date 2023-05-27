package com.example.dto.article;

import lombok.Data;

@Data
public class UpdateArticleResponseDto {
    private ArticleDto article;

    public UpdateArticleResponseDto(ArticleDto article) {
        this.article = article;
    }
}
