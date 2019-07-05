package com.grapheople.comarket.domain.item.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemRequestModel {
    @ApiModelProperty(required = true, name = "제목")
    String title;
    @ApiModelProperty(required = true, name = "본문")
    String body;
    @ApiModelProperty(required = true, name = "거래 위치")
    String location;
    @ApiModelProperty(name = "중고 가격")
    Double price;
    List<MultipartFile> images;
}
