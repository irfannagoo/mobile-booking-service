package com.example.mobilebooking.service;

import com.example.mobilebooking.request.MobileBookingRequest;
import com.example.mobilebooking.response.MobileBookingResponse;
import com.example.mobilebooking.response.BaseResponse;

/**
 * @author irfan.nagoo
 * @created 10-03-2023
 */
public interface MobileService {

    /**
     * Returns the list of all the mobile phones with status
     *
     * @return
     */
    MobileBookingResponse getMobileAll();

    /**
     * Returns a particular mobile model
     *
     * @param modelName
     * @return
     */
    MobileBookingResponse getMobile(String modelName);

    /**
     * Books the mobile against a particular individual
     *
     * @param request
     * @return
     */
    BaseResponse bookMobile(MobileBookingRequest request);

    /**
     * Returns back the mobile taken by an individual
     *
     * @param request
     * @return
     */
    BaseResponse returnMobile(MobileBookingRequest request);
}
