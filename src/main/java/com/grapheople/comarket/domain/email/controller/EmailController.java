package com.grapheople.comarket.domain.email.controller;

import com.grapheople.comarket.domain.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("email/v1")
public class EmailController {
    private final EmailService emailService;

    @GetMapping("test")
    public Object sendMailTest() {
        emailService.sendSimpleMessage("magmacannon@gmail.com", "hello", "world");
        return null;
    }

}
