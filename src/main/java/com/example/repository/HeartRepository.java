package com.example.repository;

import com.example.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long>  {
    Optional<Heart> findByArticleIdAndMemberId(Long articleId, Long memberId);
}
