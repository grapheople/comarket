package com.grapheople.comarket.domain.item.controller.api;

import com.grapheople.comarket.common.wrapper.ResultResponse;
import com.grapheople.comarket.domain.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/item")
public class ItemApiController {
    private final ItemService itemService;

    @PostMapping
    public ResultResponse<Boolean> createItem() {

    }
}
