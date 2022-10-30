package com.thinkingbinding.member.service;

import com.thinkingbinding.member.model.RegisterParam;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

public interface MemberService extends UserDetailsService {

    /*회원 가입*/
    @Transactional
    void register(RegisterParam param);

    /*계정 활성화하기*/
    @Transactional
    void emailAuth(String emailAuthKey);
}
