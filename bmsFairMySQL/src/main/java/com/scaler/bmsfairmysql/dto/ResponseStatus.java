package com.scaler.bmsfairmysql.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseStatus {
    private String message;
    private StatusCode code;
}
