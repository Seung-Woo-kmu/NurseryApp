package com.example.api.controller;

import com.example.dto.article.ArticleDto;
import com.example.dto.article.GetArticleListRequestDto;
import com.example.dto.GetNurseryListResponseDto;
import com.example.dto.article.GetArticleListResponseDto;
import com.example.dto.article.GetArticleResponseDto;
import com.example.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/api/articleList")
    public ResponseEntity<GetArticleListResponseDto> showNurseryList(
            @RequestParam GetArticleListRequestDto request
    ) {
        return ResponseEntity
                .ok()
                .body(new GetArticleListResponseDto(articleService.findAll().stream().map(ArticleDto::new).toList()));
    }

    @GetMapping("/api/article/{id}")
    public ResponseEntity<GetArticleResponseDto> showNurseryList(
            @PathVariable("id") Long articleId
    ) {
        return ResponseEntity
                .ok()
                .body(new GetArticleListResponseDto(articleService.findById(articleId).map(ArticleDto::new).toList()));
    }

}