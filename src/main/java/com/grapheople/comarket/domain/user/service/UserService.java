package com.grapheople.comarket.domain.user.service;

import com.grapheople.comarket.common.exception.ComarketException;
import com.grapheople.comarket.domain.auth.persistence.entity.EmailAuth;
import com.grapheople.comarket.domain.user.persistence.entity.User;
import com.grapheople.comarket.domain.user.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final StringEncryptor stringEncryptor;
    @Transactional
    public User createUser(EmailAuth emailAuth) {
        User newUser = new User();
        newUser.setNickName(emailAuth.getEmail().substring(0,2).concat(stringEncryptor.encrypt(emailAuth.getEmail()).substring(0,12)));
        newUser.setEmailAuthId(emailAuth.getId());
        return userRepository.save(newUser);
    }

    @Transactional
    public User updateEmailAuth(EmailAuth emailAuth, String accountName) {
        User user = userRepository.findByAccountName(accountName).orElseThrow(() -> new UserServiceException(40001, "사용자가 존재하지 않습니다."));
        user.setEmailAuthId(emailAuth.getId());
        return userRepository.save(user);
    }

    public class UserServiceException extends ComarketException {
        public UserServiceException(int code, String message) {
            super(HttpStatus.BAD_REQUEST, code, message);
        }
    }
}
