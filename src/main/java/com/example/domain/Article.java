package com.example.domain;

import com.example.domain.nursery.CityDistrict;
import com.example.enums.BoardType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends ExistTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private BoardType boardType;

    @Column
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column
    private List<Long> likedUserIds;

    @Column
    private String userProfileImage; //url

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private List<Integer> likesCount;

    @Column
    @OneToMany(mappedBy = "article" ,cascade = CascadeType.ALL)
    private List<Comment> comments;
}
