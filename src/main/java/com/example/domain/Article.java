package com.example.domain;

import com.example.domain.nursery.CityDistrict;
import com.example.dto.article.ArticleDto;
import com.example.enums.BoardType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends ExistTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column
    private BoardType boardType;

    @Column
    private String title;

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy="article", cascade = CascadeType.ALL)
    private List<Heart> hearts = new ArrayList<Heart>();

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<Comment>();

    private void setBoardType(BoardType boardType) {
        this.boardType = boardType;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setContent(String content) {
        this.content = content;
    }

    public Article(BoardType boardType, String title, String content, Member member) {
        this.boardType = boardType;
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public void updateArticle(
            BoardType boardType,
            String title,
            String content
    ) {
        this.setBoardType(boardType);
        this.setTitle(title);
        this.setContent(content);
    }

    public void removeComment(Comment comment) {
        this.comments.remove(comment);
        this.getMember().getComments().remove(comment);
    }

}
