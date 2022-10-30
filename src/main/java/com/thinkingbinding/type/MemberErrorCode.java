package com.thinkingbinding.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberErrorCode {

    LOGIN_FAIL("로그인에 실패하셨습니다."),
    USER_EMAIL_ALREADY_EXIST("이미 존재하는 이메일입니다."),
    EMAIL_AUTH_KEY_NOT_FOUND("해당 이메일 인증키가 존재하지 않습니다."),
    EMAIL_AUTH_KEY_ALREADY_EXIST("이미 인증된 이메일입니다."),
    USER_INFO_NOT_FOUND("회원 정보가 존재하지 않습니다"),
    USER_EMAIL_AUTH_NOT_YET("이메일 인증 후 로그인해 주세요."),
    USER_STATUS_STOP_MEMBER("정지된 회원입니다."),
    USER_STATUS_WITHDRAW_MEMBER("탈퇴한 회원입니다.");



    private final String description;
}
