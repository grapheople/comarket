package com.grapheople.comarket.common.service;

import com.grapheople.comarket.common.exception.ComarketException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Sets;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {
    private static final String REGEX_EMAIL_VALIDATION = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-zA-Z]{2,})$";
    private static final Set<String> WHITE_COMPANY_DOMAIN = Sets.newTreeSet(
            "gmail.com",
            "kakaocorp.com"
    );
    private final JavaMailSender emailSender;


    public void sendSimpleMessage(String to, String subject, String text) {
        validateEmail(to);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        try {
            emailSender.send(message);
        } catch (MailException e) {
            throw new EmailException(40003, "이메일 발송실패");
        }
    }

    public void validateEmail(String email) {
        if (!email.matches(REGEX_EMAIL_VALIDATION)) throw new EmailException(40001, "잘못된 이메일 형식입니다.");

        String domain = email.substring(email.indexOf('@') + 1);
        if (!WHITE_COMPANY_DOMAIN.contains(domain)) throw new EmailException(40002, "지원하지 않는 회사 도메인입니다.");
    }

    public class EmailException extends ComarketException {
        public EmailException(int code, String message) {
            super(HttpStatus.BAD_REQUEST, code, message);
        }
    }
}
