package com.example.web.controller;


import com.example.domain.Authorization;
import com.example.domain.Gender;
import com.example.domain.Member;
import com.example.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @PostMapping("/api/users")
    public CreateMemberResponse addMembers(@RequestBody @Validated Member newMember) {
        Member member = new Member(newMember.getLoginId(), newMember.getPassword(), newMember.getName(), newMember.getNickName(), newMember.getNurseryName(), newMember.getPhoneNumber(), Authorization.NORMAL, newMember.getGender());
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
        private String loginId;
        private String password;
        private String name;
        private String nickName;
        private String nurseryName;
        private String phoneNumber;
        private Gender gender;
    }
    @Data
    @AllArgsConstructor
    static class CreateMemberResponse {
        private Long id;
    }
}
