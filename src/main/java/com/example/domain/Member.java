package com.example.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public Member(String loginId, String password, String name, String nickName, String nurseryName, String phoneNumber, Authorization auth, Gender gender) {
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

    @Column(unique = true)
    private String nickName;

    private String nurseryName;

    @Column(unique = true)
    private String phoneNumber;

    @Enumerated(value = EnumType.STRING)
    private Authorization auth;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;
}
