package com.example.domain;

import com.example.domain.nursery.CityDistrict;
import com.example.enums.BoardType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private Long userId;

    @Column
    private String userName;

    @Column
    private String userProfileImage; //url

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String likesCount;

    @Column
    private Comment comments;

    @Column
    private int countLiked;

}
