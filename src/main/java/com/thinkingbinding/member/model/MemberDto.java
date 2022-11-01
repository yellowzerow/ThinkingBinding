package com.thinkingbinding.member.model;

import com.thinkingbinding.member.entity.Member;
import com.thinkingbinding.type.UserStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {

    private Long userId;
    private String userEmail;
    private String password;
    private String userName;
    private String nickName;
    private String motd;
    private String profileImage;
    private UserStatus userStatus;
    private boolean adminYn;
    private boolean emailAuthYn;
    private String emailAuthKey;
    private LocalDateTime regDt;
    private LocalDateTime udtDt;


    public static MemberDto of(Member member) {
        return MemberDto.builder()
                .userId(member.getUserId())
                .userEmail(member.getUserEmail())
                .password(member.getPassword())
                .userName(member.getUserName())
                .nickName(member.getNickName())
                .motd(member.getMotd())
                .profileImage(member.getProfileImage())
                .userStatus(member.getUserStatus())
                .adminYn(member.isAdminYn())
                .emailAuthYn(member.isEmailAuthYn())
                .emailAuthKey(member.getEmailAuthKey())
                .regDt(member.getRegDt())
                .udtDt(member.getUdtDt())
                .build();
    }

    public String getRegDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return regDt != null ? regDt.format(formatter) : "";
    }

    public String getUdtDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return udtDt != null ? udtDt.format(formatter) : "";
    }
}
