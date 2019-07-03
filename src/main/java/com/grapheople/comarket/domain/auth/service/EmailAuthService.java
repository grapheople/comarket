package com.grapheople.comarket.domain.auth.service;

import com.grapheople.comarket.common.exception.ComarketException;
import com.grapheople.comarket.domain.auth.persistence.entity.EmailAuth;
import com.grapheople.comarket.domain.auth.persistence.repository.EmailAuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailAuthService {
    private final EmailAuthRepository emailAuthRepository;
    private final StringEncryptor jasyptStringEncryptor;

    /**
     * 새로운 사용자의 인증코드를 생성한다.
     *
     * @param email 사용자 이메일
     * @return 인증코드
     */
    @Transactional
    public EmailAuth createEmailAuth(String email) {
        emailAuthRepository.findByEmailAndAuthAtIsNotNull(email).ifPresent(emailAuth -> {
            if(emailAuth.getAuthAt() != null) throw new EmailAuthException(40001, "이미 인증된 사용자입니다.");
            emailAuthRepository.delete(emailAuth);
            emailAuthRepository.flush();
        });
        EmailAuth emailAuth = new EmailAuth();
        emailAuth.setEmail(email);
        emailAuth.setAuthCode(jasyptStringEncryptor.encrypt(email));
        emailAuth.setMailSendAt(LocalDateTime.now());
        return emailAuthRepository.save(emailAuth);
    }

    /**
     * 인증 요청을 처리한다
     * @param email 사용자 이메일
     * @param authCode 이메일 링크에 포함된 인증코드
     * @return 인증된 엔티티 ID
     */
    @Transactional
    public EmailAuth authEmail(String email, String authCode) {
        EmailAuth emailAuth = emailAuthRepository.findByEmailAndAuthAtIsNotNull(email).orElseThrow(() -> new EmailAuthException(40002, "존재하지 않는 메일입니다."));
        if (Duration.between(emailAuth.getMailSendAt(), LocalDateTime.now()).getSeconds() > 600) throw new EmailAuthException(40003, "인증 시간이 초과되었습니다.");
        if(!emailAuth.getAuthCode().equals(authCode)) throw new EmailAuthException(40004, "인증 코드가 불일치합니다.");
        emailAuth.setAuthAt(LocalDateTime.now());
        return emailAuthRepository.save(emailAuth);
    }



    public class EmailAuthException extends ComarketException {
        public EmailAuthException(int code, String message) {
            super(HttpStatus.BAD_REQUEST, code, message);
        }
    }
}
