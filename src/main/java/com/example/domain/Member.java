package com.example.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends ExistTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String loginId;

    private String password;

    public Member(String loginId, String password, String name, String nickName, String nurseryName, String phoneNumber, Authority auth, Gender gender) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
        this.nurseryName = nurseryName;
        this.phoneNumber = phoneNumber;
        this.auth = auth;
        this.gender = gender;
    }

    public Member(String loginId, String password, String name, String nickName, String nurseryName, String phoneNumber, Gender gender) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
        this.nurseryName = nurseryName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    private String name;

    private String nickName;

    private String nurseryName;

    private String phoneNumber;

    @Enumerated(value = EnumType.STRING)
    private Authority auth;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;
}
