package com.example.service;

import com.example.api.JwtUtils;
import com.example.domain.Article;
import com.example.domain.ArticleImage;
import com.example.domain.Member;
import com.example.dto.article.ArticleDto;
import com.example.dto.article.CreateArticleRequestDto;
import com.example.dto.article.GetArticleListRequestDto;
import com.example.dto.article.UpdateArticleRequestDto;
import com.example.repository.ArticleImageRepository;
import com.example.repository.ArticleRepository;
import com.example.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {
    @Value("${file.dir}")
    private String filePath;
    private final ArticleRepository articleRepository;
    private final ArticleImageRepository articleImageRepository;
    private final MemberRepository memberRepository;
    private final JwtUtils jwtUtils;


    public List<ArticleDto> findAll(String token, GetArticleListRequestDto request) {
        Long memberId = jwtUtils.getMemberId(token);
        List<Article> articleList = articleRepository.findByBoardType(request.getBoardType());
        articleList.sort(
                switch (request.getSortType()) {
                    case latest -> Comparator.comparing(Article::getCreatedTime);
                    case like -> Comparator.comparing(article -> article.getHearts().size());
                    case comment -> Comparator.comparing(article -> article.getComments().size());
                }
        );
        return articleList.stream().map(article -> new ArticleDto(article, memberId)).toList();
    }

    public ArticleDto findById(Long articleId, String token) {
        Long memberId = jwtUtils.getMemberId(token);
        return new ArticleDto(articleRepository.findById(articleId).get(), memberId);
    }

    @Transactional
    public ArticleDto createArticle(CreateArticleRequestDto request, String token, List<MultipartFile> files) throws IOException {
        Member member = memberRepository.findById(jwtUtils.getMemberId(token)).get();
        Article article = new Article(
                request.getBoardType(),
                request.getTitle(),
                request.getContent(),
                member
        );
        if (files != null && !files.isEmpty()) {
            for (MultipartFile file: files) {
                String fileName = filePath + file.getOriginalFilename();
                File imageFile = new File(fileName);
                file.transferTo(imageFile);
                ArticleImage articleImage = new ArticleImage(file.getOriginalFilename(), article);
                articleImageRepository.save(articleImage);
                article.addImage(articleImage);
            }
        }
        articleRepository.save(article);
        return new ArticleDto(article, article.getMember().getId());
    }

    @Transactional
    public ArticleDto updateArticle(Long articleId, UpdateArticleRequestDto request, List<MultipartFile> files) throws IOException {
        Article article = articleRepository.findById(articleId).get();
        article.updateArticle(
                request.getBoardType(),
                request.getTitle(),
                request.getContent()
        );
        List<ArticleImage> removeArticleImages = article.getImages().stream().filter(image -> !request.getAttachedImageURLs().contains(image.getImageUrl())).toList();
        for (ArticleImage image : removeArticleImages) {
            articleImageRepository.delete(image);
            article.removeImage(image);
        }
        if (files != null && !files.isEmpty()) {
            for (MultipartFile file: files) {
                String fileName = filePath + file.getOriginalFilename();
                File imageFile = new File(fileName);
                file.transferTo(imageFile);
                ArticleImage articleImage = new ArticleImage(file.getOriginalFilename(), article);
                articleImageRepository.save(articleImage);
                article.addImage(articleImage);
            }
        }
        articleRepository.save(article);
        return new ArticleDto(article, article.getMember().getId());
    }

    @Transactional
    public Long deleteArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).get();
        articleRepository.delete(article);
        return articleId;
    }
}
