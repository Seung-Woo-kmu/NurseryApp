package com.example.dto.article;

import com.example.domain.Comment;
import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
public class CommentDto {
    private Long id;
    private Long userId;
    private String userName;
    private String userProfileImage; //url
    private String content;
    private String createdAt;
    private Boolean isOwner; //by current user

    public CommentDto(Comment comment, Long memberId) {
        this.id = comment.getId();
        this.userId = comment.getMember().getId();
        this.userName = comment.getMember().getNickName();
        this.userProfileImage = comment.getMember().getProfileImageUrl();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.isOwner = userId.equals(memberId);
    }
}
