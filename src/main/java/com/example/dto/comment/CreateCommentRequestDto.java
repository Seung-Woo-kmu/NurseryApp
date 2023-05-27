package com.example.dto.comment;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreateCommentRequestDto {
    private String content;
}
