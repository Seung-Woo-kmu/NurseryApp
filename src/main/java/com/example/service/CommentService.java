package com.example.service;

import com.example.api.JwtUtils;
import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.domain.Member;
import com.example.dto.comment.CreateCommentRequestDto;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;
import com.example.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final JwtUtils jwtUtils;

    @Transactional
    public Long createComment(Long articleId, String token, CreateCommentRequestDto request) {
        Long memberId = jwtUtils.getMemberId(token);
        Member member = memberRepository.findById(memberId).get();
        Article article = articleRepository.findById(articleId).get();
        return commentRepository.save(new Comment(request.getContent(), article, member)).getId();
    }

    @Transactional
    public Long deleteComment(Long articleId, Long commentId, String token) {
        Long memberId = jwtUtils.getMemberId(token);
        Article article = articleRepository.findById(articleId).get();
        if (!article.getComments().stream().map(comment -> comment.getId()).toList().contains(commentId))
            throw new IllegalArgumentException("게시판에 없는 댓글입니다.");
        if (!article.getMember().getId().equals(memberId))
            throw new IllegalArgumentException("본인이 작성한 댓글만 삭제할 수 있습니다.");
        Comment comment = commentRepository.findById(commentId).get();
        article.removeComment(comment);
        commentRepository.delete(comment);
        return commentId;
    }

}
