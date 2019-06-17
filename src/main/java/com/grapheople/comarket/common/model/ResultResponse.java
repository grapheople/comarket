package com.grapheople.comarket.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultResponse<T> {
    private int code;
    private String message;
    private T result;

    public ResultResponse(T result) {
        this.code = 0;
        this.message = "test";
        this.result = result;
    }
}
