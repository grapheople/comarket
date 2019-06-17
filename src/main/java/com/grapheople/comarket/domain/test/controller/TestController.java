package com.grapheople.comarket.domain.test.controller;

import com.grapheople.comarket.common.model.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("test")
public class TestController {
    @GetMapping
    public ResultResponse<String> doTest() {
        return new ResultResponse<>("do test");
    }
}
