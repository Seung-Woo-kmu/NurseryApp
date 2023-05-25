package com.example.dto;

import com.example.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMember {
    private Long id;
    @NotBlank(message = "공백일 수 없습니다.")
    @Length(max = 20, message = "최대 길이는 12입니다.")
    private String loginId;
    @NotBlank(message = "공백일 수 없습니다.")
    @Length(min = 10, max = 20, message = "비밀번호의 길이는 10 ~ 20입니다.")
    private String password;
    @NotBlank(message = "공백일 수 없습니다.")
    @Length(max = 20, message = "비밀번호의 최대 길이는 20입니다.")
    private String name;
    @NotBlank(message = "공백일 수 없습니다.")
    @Length(max = 20, message = "비밀번호의 최대 길이는 20입니다.")
    private String nickName;
    @NotBlank(message = "공백일 수 없습니다.")
    private String nurseryName;
    @NotBlank(message = "공백일 수 없습니다.")
    @Length(max = 12, message = "최대 길이는 12입니다.")
    private String phoneNumber;
    private Gender gender;
}