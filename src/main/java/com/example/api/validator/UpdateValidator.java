package com.example.api.validator;

import com.example.domain.Member;
import com.example.dto.UpdateMember;
import com.example.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UpdateValidator implements Validator {

    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UpdateMember.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        UpdateMember member = (UpdateMember) target;
        Optional<Member> existMember = memberRepository.findByLoginId(member.getLoginId());
        Optional<Member> dupMember = existMember.filter(m -> m.getId().equals(member.getId()));

        if (existMember.isPresent() && dupMember.isEmpty()) {
            errors.rejectValue("LoginId", "loginIdError", "이미 존재하는 아이디입니다.");
        }
        Optional<Member> niMember = memberRepository.findByNickName(member.getNickName());
        Optional<Member> dMember = niMember.filter(m -> m.getId().equals(member.getId()));

        if (niMember.isPresent() && dMember.isEmpty()) {
            errors.rejectValue("nickName", "nickNameError", "이미 존재하는 닉네임입니다");
        }
    }
}
