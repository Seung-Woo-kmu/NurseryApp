package com.example.dto.article;

import com.example.enums.BoardType;
import lombok.Data;

import java.util.List;

@Data
public class UpdateArticleRequestDto {
    private BoardType boardType;
    private String title;
    private String content;
    private List<String> attachedImageURLs;
}
