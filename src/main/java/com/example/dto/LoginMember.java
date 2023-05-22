package com.example.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class LoginMember {
    @NotBlank
    @Length(max = 20)
    private String loginId;

    @NotBlank
    @Length(min = 8, max = 20)
    private String password;

    public LoginMember() {
    }
}
