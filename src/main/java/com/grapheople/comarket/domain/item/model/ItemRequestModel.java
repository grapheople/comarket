package com.grapheople.comarket.domain.item.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ItemRequestModel {
    String title;
    String body;
    Double price;
    List<MultipartFile> images;
}
