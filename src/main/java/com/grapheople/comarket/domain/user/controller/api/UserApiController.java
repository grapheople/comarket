package com.grapheople.comarket.domain.user.controller.api;

import com.grapheople.comarket.common.wrapper.ResultResponse;
import com.grapheople.comarket.domain.user.persistence.entity.User;
import com.grapheople.comarket.domain.user.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserApiController {
    private final StringEncryptor stringEncryptor;
    private final UserRepository userRepository;

    @GetMapping("test")
    public ResultResponse<User> test(@RequestParam String nickName, @RequestParam String password) {
        User user = new User();
        user.setAccountName(nickName);
        user.setNickName(nickName);
        user.setPassword(stringEncryptor.encrypt(password));
        return new ResultResponse<>(userRepository.save(user));
    }
}
