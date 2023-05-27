package com.example.api.controller;

import com.example.dto.heart.CreateHeartResponseDto;
import com.example.dto.heart.DeleteHeartResponseDto;
import com.example.service.HeartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = {"게시판 좋아요 API"})
public class HeartController {
    private final HeartService heartService;

    @ApiOperation(value = "좋아요 추가")
    @PostMapping("/api/article/{articleId}/heart")
    public ResponseEntity<CreateHeartResponseDto> createHeart(
            @PathVariable("articleId") Long articleId,
            @RequestHeader("Authorization") String token
    ) {
        return ResponseEntity
                .ok()
                .body(new CreateHeartResponseDto(heartService.createHeart(articleId, token)));
    }

    @ApiOperation(value = "좋아요 삭제")
    @DeleteMapping("/api/article/{articleId}/heart/{heartId}")
    public ResponseEntity<DeleteHeartResponseDto> deleteHeart(
            @PathVariable("articleId") Long articleId,
            @PathVariable("heartId") Long heartId,
            @RequestHeader("Authorization") String token
    ) {
        return ResponseEntity
                .ok()
                .body(new DeleteHeartResponseDto(heartService.deleteHeart(articleId, heartId, token)));
    }

}
