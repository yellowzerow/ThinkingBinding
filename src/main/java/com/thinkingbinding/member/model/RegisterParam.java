package com.thinkingbinding.member.model;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
public class RegisterParam {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식이 올바르지 않습니다. 다시 입력해주세요.")
    private String userEmail;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(message = "8자 ~ 20자, 영소문자와 숫자가 최소 1개 이상 포함되어야 합니다."
            ,regexp = "^(?=.*\\d)(?=.*[a-z])[\\da-z]{8,20}$")
    private String password;

    @NotBlank(message = "이름을 입력해주세요.")
    @Min(2)
    private String userName;

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickName;

}
