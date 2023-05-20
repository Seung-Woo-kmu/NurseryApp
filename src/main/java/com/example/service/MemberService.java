package com.example.service;

import com.example.domain.member.Member;
import com.example.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public List<Member> findAll(){
        return memberRepository.findAll();
    }
    @Transactional
    public Long addMember(Member user) {
        memberRepository.save(user);
        return user.getId();
    }
}
