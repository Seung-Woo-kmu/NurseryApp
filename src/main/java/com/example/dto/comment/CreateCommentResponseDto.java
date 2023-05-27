package com.example.dto.comment;

import lombok.Data;

@Data
public class CreateCommentResponseDto {
    private Long id;

    public CreateCommentResponseDto(Long id) {
        this.id = id;
    }
}
