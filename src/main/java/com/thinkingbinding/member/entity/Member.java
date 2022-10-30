package com.thinkingbinding.member.entity;

import com.thinkingbinding.type.UserStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;            // 회원 고유 id 값

    private String userEmail;       // 회원 아이디(이메일)
    private String password;        // 비밀번호
    private String userName;        // 회원 이름
    private String nickName;        // 회원 닉네임
    private String motd;            // 회원 상태 메세지
    private String profileImage;    // 회원 프로필 사진 이미지
    private LocalDateTime udtDt;    // 정보 수정일

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;  // 회원 활동 상태
    private boolean adminYn;        // 관리자 여부
    private boolean emailAuthYn;    // 이메일 인증 여부
    private String emailAuthKey;    // 이메일 인증 키
    private LocalDateTime regDt;    // 가입일
}
