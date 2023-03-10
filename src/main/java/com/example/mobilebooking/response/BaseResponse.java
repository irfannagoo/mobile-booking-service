package com.example.mobilebooking.response;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author irfan.nagoo
 * @created 10-03-2023
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {

    private final String code;
    private final String message;

    public BaseResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
