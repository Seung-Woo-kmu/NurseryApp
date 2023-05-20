package com.example.web.controller;


import com.example.domain.member.Authorization;
import com.example.domain.member.Gender;
import com.example.domain.member.Member;
import com.example.service.MemberService;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/api/users")
    public MemberList showUsers() {
        return new MemberList(memberService.findAll()
                .stream()
                .map(u -> new MemberDto(u.getId(),u.getLoginId(),u.getPassword(), u.getName(), u.getNickName(), u.getNurseryName(), u.getPhoneNumber(), u.getAuth(), u.getGender()))
                .collect(Collectors.toList()));
    }
    @PostMapping("/api/users/register")
    public CreateMemberResponse addMembers(@RequestBody @Validated Member newMember) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Member member = new Member(newMember.getLoginId(), passwordEncoder.encode(newMember.getPassword()), newMember.getName(), newMember.getNickName(), newMember.getNurseryName(), newMember.getPhoneNumber(), Authorization.NORMAL, newMember.getGender());
        Long memberId = memberService.addMember(member);
        return new CreateMemberResponse(memberId);
    }

    @Data
    @AllArgsConstructor
    static class MemberList<T> {
        private T members;
    }
    @Data
    @AllArgsConstructor
    static class MemberDto {
        private Long id;
        private String loginId;
        private String password;
        private String name;
        private String nickName;
        private String nurseryName;
        private String phoneNumber;
        private Authorization auth;
        private Gender gender;
    }
    @Data
    @AllArgsConstructor
    static class CreateMember {
        @NotBlank
        @Length(max = 20)
        private String loginId;
        @NotBlank
        @Length(min = 10, max = 20)
        private String password;
        @NotBlank
        @Length(max = 20)
        private String name;
        @NotBlank
        @Length(max = 20)
        private String nickName;
        @NotBlank
        private String nurseryName;
        @NotBlank
        @Length(max = 12)
        private String phoneNumber;
        @NotBlank
        private Gender gender;
    }
    @Data
    @AllArgsConstructor
    static class CreateMemberResponse {
        private Long id;
    }
}
