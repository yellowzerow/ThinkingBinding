package com.thinkingbinding.member.controller;

import com.thinkingbinding.member.model.RegisterParam;
import com.thinkingbinding.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 가입 페이지 이동
     */
    @GetMapping("/register")
    public String register() {
        return "member/register";
    }

    /**
     * 회원 가입하기
     */
    @PostMapping("register")
    public String registerSubmit(
            Model model
            , @Valid RegisterParam param
    ) {
        memberService.register(param);

        return "member/register_success";
    }

    /**
     * 이메일 인증하기
     * */
    @GetMapping("/email-auth")
    public String emailAuth(
            Model model
            , HttpServletRequest request
    ) {
        String emailAuthKey = request.getParameter("id");

        memberService.emailAuth(emailAuthKey);

        return "member/email_auth";
    }

    /**
     * 로그인하기
     */
    @RequestMapping("/login")
    public String login () {
        return "member/login";
    }

}
