package com.example.service;

import com.example.domain.Article;
import com.example.domain.nursery.Nursery;
import com.example.repository.ArticleRepository;
import com.example.repository.NurseryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findById(Long articleId) {
        return articleRepository.findById(articleId).get();
    }

    @Transactional
    public Long addArticle(Article article) {
        articleRepository.save(article);
        return article.getId();
    }
}
