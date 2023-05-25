package com.example.dto.article;

import com.example.domain.Article;
import lombok.Data;

@Data
public class ArticleDto {
    private Long id;
    private String boardType;
    private Long userId;
    private String userName;
    private String userProfileImage; //url
    private String title;
    private String content;
    private String createdAt;
    private String likesCount;
    private CommentDto comments;
    private Boolean liked; //by current user
    private Boolean isOwner; //by current user

    public ArticleDto(Article article) {
        this.id = article.getId();
        this.boardType = article.getBoardType();
        this.userId = article.getUserId();
        this.userName = article.getUserName();
        this.userProfileImage = article.getUserProfileImage();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedTime().toString();
        this.likesCount = article.getLikesCount();
        this.comments = article.co;
        this.liked = liked;
        this.isOwner = isOwner;
    }
}
