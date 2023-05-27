package com.example.dto.article;

import com.example.domain.Article;
import com.example.domain.Heart;
import com.example.domain.Member;
import com.example.enums.BoardType;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class ArticleDto {
    private Long id;
    private BoardType boardType;
    private Long userId;
    private String userName;
    private String userProfileImage; //url
    private String title;
    private String content;
    private String createdAt;
    private Integer likesCount;
    private List<CommentDto> comments;
    private Boolean liked; //by current user
    private Boolean isOwner; //by current user

    public ArticleDto(Article article, Long memberId) {
        Member createMember = article.getMember();
        this.id = article.getId();
        this.boardType = article.getBoardType();
        this.userId = createMember.getId();
        this.userName = createMember.getName();
        this.userProfileImage = createMember.getProfileImageUrl();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.likesCount = article.getHearts().size();
        this.comments = article.getComments().stream().map(comment -> new CommentDto(comment, memberId)).toList();
        this.liked = article.getHearts().stream()
                .map(Heart::getMember).toList().contains(memberId);
        this.isOwner = createMember.getId().equals(memberId);
    }
}
