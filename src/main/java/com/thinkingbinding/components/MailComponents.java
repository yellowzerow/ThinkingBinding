package com.thinkingbinding.components;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MailComponents {

    private final JavaMailSender javaMailSender;

    public void sendMail(String mail, String subject, String text) {

        MimeMessagePreparator msg = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(mail);
            helper.setSubject(subject);
            helper.setText(text, true);
        };

        try {
            javaMailSender.send(msg);
            log.info("인증 메일 전송 성공!");
        } catch (Exception e){
            log.error(e.getMessage() + ": 인증 메일 전송 실패!");
        }
    }
}
