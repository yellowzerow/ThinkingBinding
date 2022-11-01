package com.thinkingbinding.member.service.impl;

import com.thinkingbinding.components.MailComponents;
import com.thinkingbinding.exception.MemberException;
import com.thinkingbinding.member.entity.Member;
import com.thinkingbinding.member.model.RegisterParam;
import com.thinkingbinding.member.repository.MemberRepository;
import com.thinkingbinding.member.service.MemberService;
import com.thinkingbinding.type.MemberErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.thinkingbinding.type.MemberErrorCode.*;
import static com.thinkingbinding.type.UserStatus.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MailComponents mailComponents;

    /**
     * 회원 가입
     */
    @Override
    public void register(RegisterParam param) {

        Optional<Member> optionalMember = memberRepository.findByUserEmail(param.getUserEmail());
        if (optionalMember.isPresent())
            throw new MemberException(USER_EMAIL_ALREADY_EXIST);

        String emailAuthKey = UUID.randomUUID().toString();
        String encPassword = BCrypt.hashpw(param.getPassword(), BCrypt.gensalt());

        memberRepository.save(
                Member.builder()
                    .userEmail(param.getUserEmail())
                    .password(encPassword)
                    .userName(param.getUserName())
                    .nickName(param.getNickName())
                    .regDt(LocalDateTime.now())
                    .emailAuthYn(false)
                    .emailAuthKey(emailAuthKey)
                    .userStatus(REQ)
                    .build());

        String email = param.getUserEmail();
        String subject = "생각 엮기와 함께 해주셔서 감사합니다.";
        String text = "<h1>너와 나의 생각 엮기</h1>"
                    + "<p>저희 사이트에 가입해주셔서 감사합니다!</p>"
                    + "가입 완료 버튼을 클릭하시면 가입이 완료됩니다."
                    + "<div><a target='_blank' href='http://localhost:8080/member/email-auth?id="
                    + emailAuthKey + "'> 가입 완료 </a></div>";
        mailComponents.sendMail(email, subject, text);
    }

    /**
     * 이메일 인증하기
     */
    @Override
    public void emailAuth(String emailAuthKey) {

        Optional<Member> optionalMember = memberRepository.findByEmailAuthKey(emailAuthKey);
        /*해당 이메일 인증키가 DB에 저장된 인증키와 일치하지 않음*/
        if (optionalMember.isEmpty()) {
            throw new MemberException(EMAIL_AUTH_KEY_NOT_FOUND);
        }

        Member member = optionalMember.get();
        /*이미 인증 완료된 이메일*/
        if (member.isEmailAuthYn()) {
            throw new MemberException(EMAIL_AUTH_KEY_ALREADY_EXIST);
        }

        member.setUserStatus(ING);
        member.setEmailAuthYn(true);
        memberRepository.save(member);

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> optionalMember = memberRepository.findByUserEmail(username);
        if (optionalMember.isEmpty()) {
            throw new MemberException(USER_INFO_NOT_FOUND);
        }

        Member member = optionalMember.get();

        /*회원 인증 요청중인 상태일때 로그인 시도*/
        if (REQ.equals(member.getUserStatus())) {
            throw new MemberException(USER_EMAIL_AUTH_NOT_YET);
        }

        /*정지된 회원*/
        if (STOP.equals(member.getUserStatus())) {
            throw new MemberException(MemberErrorCode.USER_STATUS_STOP_MEMBER);
        }

        /*탈퇴한 회원*/
        if (WITHDRAW.equals(member.getUserStatus())) {
            throw new MemberException(MemberErrorCode.USER_STATUS_WITHDRAW_MEMBER);
        }

        /*관리자 권한 부여*/
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if (member.isAdminYn()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new User(member.getUserEmail(), member.getPassword(), grantedAuthorities);
    }
}
