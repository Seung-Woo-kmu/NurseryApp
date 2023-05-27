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
    public Long deleteHeart(Long articleId, Long heartId, String token) {
        Long memberId = jwtUtils.getMemberId(token);
        Article article = articleRepository.findById(articleId).get();
        if (!article.getHearts().stream().map(heart -> heart.getId()).toList().contains(heartId))
            throw new IllegalArgumentException("게시판에 없는 좋아요입니다.");
        if (!article.getMember().getId().equals(memberId))
            throw new IllegalArgumentException("본인이 추가한 좋아요만 삭제할 수 있습니다.");
        Heart heart = heartRepository.findById(heartId).get();
        article.removeHeart(heart);
        heartRepository.delete(heart);
        return heartId;
    }
}
