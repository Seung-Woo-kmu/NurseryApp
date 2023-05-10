package com.example;

import com.example.domain.Authorization;
import com.example.domain.Gender;
import com.example.domain.Member;
import com.example.service.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitData {

    private final MemberService memberService;

    @PostConstruct
    public void members() {
        Member memberA = new Member("asd12", "qwer1234", "유승우", "천재", "하니어린이집", "010-1111-1111", Authorization.NORMAL, Gender.MAN);
        Member memberB = new Member("qwe123", "qwer1234", "유태근", "바보", "하니어린이집", "010-2222-1111", Authorization.NORMAL, Gender.MAN);
        Member memberC = new Member("qwe123s", "qwer1234s", "이지원", "개천재", "하니어린이집", "010-2222-1234", Authorization.NORMAL, Gender.MAN);
        memberService.addMember(memberA);
        memberService.addMember(memberB);
        memberService.addMember(memberC);
    }
}
