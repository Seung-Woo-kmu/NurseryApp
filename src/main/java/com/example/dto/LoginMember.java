package com.example.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class LoginMember {
    @NotBlank(message = "공백일 수 없습니다.")
    @Length(max = 20, message = "최대 길이는 20입니다.")
    private String loginId;

    @NotBlank(message = "공백일 수 없습니다.")
    @Length(min = 10, max = 20, message = "비밀번호의 길이는 10 ~ 20입니다.")
    private String password;

    public LoginMember() {
    }
}
