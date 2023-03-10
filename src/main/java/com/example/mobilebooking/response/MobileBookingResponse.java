package com.example.mobilebooking.response;

import com.example.mobilebooking.domain.Mobile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * @author irfan.nagoo
 * @created 10-03-2023
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MobileBookingResponse extends BaseResponse {

    private final List<Mobile> mobileList = new ArrayList<>();

    public MobileBookingResponse(String code, String message) {
        super(code, message);
    }

    public List<Mobile> getMobileList() {
        return mobileList;
    }
}
