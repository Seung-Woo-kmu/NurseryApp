package com.example.repository;

import com.example.domain.ArticleImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleImageRepository extends JpaRepository<ArticleImage, Long> {
}
