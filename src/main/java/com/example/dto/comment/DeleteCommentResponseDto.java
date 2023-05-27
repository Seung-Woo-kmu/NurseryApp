package com.example.dto.comment;

import lombok.Data;

@Data
public class DeleteCommentResponseDto {
    private Long deleteCommentId;

    public DeleteCommentResponseDto(Long deleteCommentId) {
        this.deleteCommentId = deleteCommentId;
    }
}
