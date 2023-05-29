package com.example.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleImage extends ExistTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_image_id")
    private Long id;

    @Column
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="article_id")
    private Article article;

    public ArticleImage(String imageUrl, Article article) {
        this.imageUrl = imageUrl;
        this.article = article;
    }
}
