package com.example.dto;

import com.example.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class CreateMember {
    public CreateMember() {
    }
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
    private Gender gender;
}
