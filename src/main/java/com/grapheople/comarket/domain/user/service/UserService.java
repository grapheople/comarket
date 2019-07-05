package com.grapheople.comarket.domain.user.service;

import com.grapheople.comarket.common.exception.ComarketException;
import com.grapheople.comarket.common.utils.EmailUtils;
import com.grapheople.comarket.domain.auth.persistence.entity.EmailAuth;
import com.grapheople.comarket.domain.company.persistence.entity.Company;
import com.grapheople.comarket.domain.company.persistence.repository.CompanyRepository;
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
    public User createUser(EmailAuth emailAuth, Company company) {
        User newUser = new User();
        newUser.setCompanyId(company.getId());
        newUser.setNickName(generateDefaultNickName(emailAuth.getEmail()));
        newUser.setEmailAuthId(emailAuth.getId());

        return userRepository.save(newUser);
    }

    @Transactional
    public User updateEmailAuth(EmailAuth emailAuth, String accountName, Company company) {
        User user = userRepository.findByAccountName(accountName).orElseThrow(() -> new UserServiceException(40001, "사용자가 존재하지 않습니다."));
        user.setCompanyId(company.getId());
        user.setEmailAuthId(emailAuth.getId());
        return userRepository.save(user);
    }

    private String generateDefaultNickName(String email) {
        return email.substring(0,2).concat(stringEncryptor.encrypt(email).substring(0,12));
    }

    public class UserServiceException extends ComarketException {
        public UserServiceException(int code, String message) {
            super(HttpStatus.BAD_REQUEST, code, message);
        }
    }
}
