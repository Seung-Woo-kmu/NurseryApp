package com.example.api.controller;

import com.example.dto.comment.CreateCommentRequestDto;
import com.example.dto.comment.CreateCommentResponseDto;
import com.example.dto.comment.DeleteCommentResponseDto;
import com.example.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = {"댓글 API"})
public class CommentController {
    private final CommentService commentService;

    @ApiOperation(value = "댓글 작성")
    @PostMapping("/api/article/{articleId}/comment")
    public ResponseEntity<CreateCommentResponseDto> createComment(
            @PathVariable("articleId") Long articleId,
            @RequestHeader("Authorization") String token,
            @RequestBody CreateCommentRequestDto request
    ) {
        return ResponseEntity
                .ok()
                .body(new CreateCommentResponseDto(commentService.createComment(articleId, token, request)));
    }

    @ApiOperation(value = "댓글 삭제")
    @DeleteMapping("/api/article/{articleId}/comment/{commentId}")
    public ResponseEntity<DeleteCommentResponseDto> deleteComment(
            @PathVariable("articleId") Long articleId,
            @PathVariable("commentId") Long commentId,
            @RequestHeader("Authorization") String token
    ) {
        return ResponseEntity
                .ok()
                .body(new DeleteCommentResponseDto(commentService.deleteComment(articleId, commentId, token)));
    }

}
