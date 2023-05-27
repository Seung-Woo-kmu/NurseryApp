package com.example.dto.article;

import com.example.enums.BoardType;
import lombok.Data;

@Data
public class CreateArticleRequestDto {
    private BoardType boardType;
    private String title;
    private String content;
}
