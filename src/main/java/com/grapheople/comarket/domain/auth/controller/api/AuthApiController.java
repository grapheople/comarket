package com.grapheople.comarket.domain.auth.controller.api;

import com.grapheople.comarket.common.service.EmailService;
import com.grapheople.comarket.common.wrapper.ResultResponse;
import com.grapheople.comarket.domain.auth.persistence.entity.EmailAuth;
import com.grapheople.comarket.domain.auth.service.EmailAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthApiController {
    private final EmailService emailService;
    private final EmailAuthService emailAuthService;

    @PostMapping("email")
    public ResultResponse<Boolean> createEmailAuth(@RequestParam String email) throws Exception{
        emailService.validateEmail(email);
        EmailAuth savedEmailAuth = emailAuthService.createEmailAuth(email);
        String authCode = savedEmailAuth.getAuthCode();
        Long id = savedEmailAuth.getId();
        String subject = "안녕하세요. 코마켓에서 인증코드를 보내드립니다. 인증절차를 완료하세요!";
        String text = new StringBuffer().append("<div><h1>[이메일 인증]</h1>")
                .append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
                .append("<a href='http://localhost:8080/api/v1/auth/email/verify?email=")
                .append(email)
                .append("&authCode=")
                .append(URLEncoder.encode(authCode, StandardCharsets.UTF_8.toString()))
                .append("' target='_blank'>이메일 인증 확인</a></div>")
                .toString();
        return new ResultResponse<>(emailService.sendSimpleMessage(email, subject, text));
    }

    @GetMapping("email/verify")
    public ResultResponse verifyEmailAuth(@RequestParam String email, @RequestParam String authCode) {
        emailAuthService.authEmail(email, authCode);
        return new ResultResponse();
    }
}
