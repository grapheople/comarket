package com.grapheople.comarket.domain.item.enums;

import lombok.Getter;

public enum ResultCode {
    NO_TITLE("과세", "1"), TAX_FREE("면세", "2");

    @Getter private final String label;
    @Getter private final String value;

    ResultCode(String label, String value) {
        this.label = label;
        this.value = value;
    }
}
