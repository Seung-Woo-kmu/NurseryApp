package com.example.repository;

import com.example.domain.Article;
import com.example.domain.nursery.Nursery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {


}
