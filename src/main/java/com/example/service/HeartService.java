package com.example.service;

import com.example.api.JwtUtils;
import com.example.domain.Article;
import com.example.domain.Heart;
import com.example.domain.Member;
import com.example.repository.ArticleRepository;
import com.example.repository.HeartRepository;
import com.example.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;
    private final JwtUtils jwtUtils;

    @Transactional
    public Long createHeart(Long articleId, String token) {
        Long memberId = jwtUtils.getMemberId(token);
        Member member = memberRepository.findById(memberId).get();
        Article article = articleRepository.findById(articleId).get();
        Heart heart = heartRepository.save(new Heart(member, article));
        return heart.getId();
    }

    @Transactional
    public Long deleteHeart(Long articleId, String token) {
        Long memberId = jwtUtils.getMemberId(token);
        Heart heart = heartRepository.findByArticleIdAndMemberId(articleId, memberId).get();
        heart.getArticle().getHearts().remove(heart);
        heart.getMember().getHearts().remove(heart);
        heartRepository.delete(heart);
        return heart.getId();
    }
}
