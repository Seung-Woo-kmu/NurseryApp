package com.example.api.controller;

import com.example.dto.article.*;
import com.example.enums.BoardType;
import com.example.enums.SortType;
import com.example.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = {"게시판 API"})
public class ArticleController {

    private final ArticleService articleService;

    @ApiOperation(value = "게시판 목록 조회")
    @GetMapping("/api/articles")
    public ResponseEntity<GetArticleListResponseDto> getArticleList(
            @RequestParam BoardType boardType,
            @RequestParam SortType sortType,
            @RequestHeader("Authorization") String token
    ) {
        return ResponseEntity
                .ok()
                .body(new GetArticleListResponseDto(articleService.findAll(token, new GetArticleListRequestDto(boardType, sortType))));
    }

    @ApiOperation(value = "게시판 상세 조회")
    @GetMapping("/api/article/{id}")
    public ResponseEntity<GetArticleResponseDto> getArticle(
            @PathVariable("id") Long articleId,
            @RequestHeader("Authorization") String token
    ) {
        return ResponseEntity
                .ok()
                .body(new GetArticleResponseDto(articleService.findById(articleId, token)));
    }

    @ApiOperation(value = "게시글 작성")
    @PostMapping("/api/article")
    public ResponseEntity<CreateArticleResponseDto> createArticle(
            @RequestHeader("Authorization") String token,
            @RequestPart CreateArticleRequestDto request,
            @RequestPart List<MultipartFile> files
            ) throws IOException {
        return ResponseEntity
                .ok()
                .body(new CreateArticleResponseDto(articleService.createArticle(request, token, files)));
    }

    @ApiOperation(value = "게시글 수정")
    @PutMapping("/api/article/{id}")
    public ResponseEntity<UpdateArticleResponseDto> updateArticle(
            @PathVariable("id") Long articleId,
            @RequestPart UpdateArticleRequestDto request,
            @RequestPart List<MultipartFile> files
    ) throws IOException {
        return ResponseEntity
                .ok()
                .body(new UpdateArticleResponseDto(articleService.updateArticle(articleId, request, files)));
    }

    @ApiOperation(value = "게시글 삭제")
    @DeleteMapping("/api/article/{id}")
    public ResponseEntity<DeleteArticleResponseDto> deleteArticle(
            @PathVariable("id") Long articleId
    ) {
        return ResponseEntity
                .ok()
                .body(new DeleteArticleResponseDto(articleService.deleteArticle(articleId)));
    }

    @ApiOperation(value = "이미지 조회")
    @ResponseBody
    @GetMapping("/api/image/{imageUrl}")
    public ResponseEntity<?> showImage(@PathVariable("imageUrl") String profileImageUrl) throws IOException {
        Path path = Paths.get(profileImageUrl);
        byte[] imageBytes = Files.readAllBytes(path);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

}