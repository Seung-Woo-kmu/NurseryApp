package com.example.service;


import com.example.domain.Member;
import com.example.domain.UserDetailsImpl;
import com.example.dto.LoginMember;
import com.example.dto.LoginMemberResponse;
import com.example.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    private final AuthenticationManager authenticationManager;

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Optional<Member> findByNickName(String nickName) {
        return memberRepository.findByNickName(nickName);
    }
    public Optional<Member> findByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }
    @Transactional
    public Long addMember(Member user) {
        memberRepository.save(user);
        return user.getId();
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Member member = memberRepository.findByLoginId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("아이디 또는 비밀번호가 일치하지 않습니다."));
        return new UserDetailsImpl(member);
    }

    public LoginMemberResponse login(LoginMember request) throws Exception {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLoginId(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        UserDetailsImpl principal = (UserDetailsImpl) auth.getPrincipal();
        Member member = memberRepository.findByLoginId(principal.getLoginId()).get();
        return new LoginMemberResponse(member.getId());
    }
}
