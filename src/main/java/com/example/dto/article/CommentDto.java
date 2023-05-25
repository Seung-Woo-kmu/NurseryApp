package com.example.dto.article;

public class CommentDto {
    private Long id;
    private Long userId;
    private String userName;
    private String userProfileImage; //url
    private String content;
    private String createdAt;
    private Boolean isOwner; //by current user
}
