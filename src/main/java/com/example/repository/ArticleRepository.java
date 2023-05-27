package com.example.repository;

import com.example.domain.Article;
import com.example.enums.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    public List<Article> findByBoardType(BoardType boardType);
}
