package com.example.api.validator;

import com.example.dto.CreateMember;
import com.example.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class MemberValidator implements Validator {

    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateMember.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateMember member =(CreateMember) target;
        if (memberRepository.findByLoginId(member.getLoginId()).isPresent()) {
            errors.rejectValue("loginId", "loginIdError", "아이디가 이미 존재합니다.");
        }
        if (memberRepository.findByNickName(member.getNickName()).isPresent()) {
            errors.rejectValue("nickName", "passwordError", "닉네임이 이미 존재합니다.");
        }
    }
}
