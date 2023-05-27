package com.example.dto.article;

import lombok.Data;

@Data
public class DeleteArticleResponseDto {
    private Long deleteArticleId;

    public DeleteArticleResponseDto(Long deleteArticleId) {
        this.deleteArticleId = deleteArticleId;
    }
}
