package com.example.mobilebooking.service.impl;

import com.example.mobilebooking.dao.MobileDAO;
import com.example.mobilebooking.domain.Mobile;
import com.example.mobilebooking.request.MobileBookingRequest;
import com.example.mobilebooking.response.BaseResponse;
import com.example.mobilebooking.response.MobileBookingResponse;
import com.example.mobilebooking.service.MobileService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.example.mobilebooking.constants.MessageConstants.*;
import static com.example.mobilebooking.constants.MobileBookingConstants.*;

/**
 * @author irfan.nagoo
 * @created 10-03-2023
 */

@Service
public class MobileServiceImpl implements MobileService {

    private final MobileDAO mobileDAO;

    public MobileServiceImpl(MobileDAO mobileDAO) {
        this.mobileDAO = mobileDAO;
    }

    @Override
    public MobileBookingResponse getMobileAll() {
        List<Mobile> mobileList = mobileDAO.getMobileAll();
        MobileBookingResponse response = new MobileBookingResponse(SUCCESS, String.format(TOTAL_RECORD, mobileList.size()));
        response.getMobileList().addAll(mobileList);
        return response;
    }

    @Override
    public MobileBookingResponse getMobile(String modelName) {
        List<Mobile> mobileList = mobileDAO.getMobile(modelName);
        if (mobileList.isEmpty()) {
            return new MobileBookingResponse(NOT_FOUND, String.format(MOBILE_NOT_FOUND, modelName));
        }
        MobileBookingResponse response = new MobileBookingResponse(SUCCESS, String.format(TOTAL_RECORD, mobileList.size()));
        response.getMobileList().addAll(mobileList);
        return response;
    }

    @Override
    public BaseResponse bookMobile(MobileBookingRequest request) {
        List<Mobile> mobileList = mobileDAO.getMobile(request.getModelName());
        Optional<Mobile> selMobile = mobileList.stream()
                .filter(mobile -> mobile.isAvailable())
                .findFirst();
        if (selMobile.isPresent()) {
            Mobile mobile = selMobile.get();
            mobile.setAvailable(false);
            mobile.setBookedBy(request.getBookedBy());
            mobile.setBookingDate(LocalDate.now());
            mobileDAO.updateMobile(mobile, NULL);
            return new BaseResponse(SUCCESS, String.format(MOBILE_BOOKED, request.getModelName()));
        } else {
            return new BaseResponse(NOT_AVAILABLE, String.format(MOBILE_NOT_AVAILABLE, request.getModelName()));
        }
    }

    @Override
    public BaseResponse returnMobile(MobileBookingRequest request) {
        List<Mobile> mobileList = mobileDAO.getMobile(request.getModelName());
        Optional<Mobile> selMobile = mobileList.stream()
                .filter(mobile -> request.getModelName().equalsIgnoreCase(mobile.getModelName())
                        && request.getBookedBy().equalsIgnoreCase(mobile.getBookedBy())
                        && !mobile.isAvailable())
                .findFirst();
        if (selMobile.isPresent()) {
            Mobile mobile = selMobile.get();
            mobile.setAvailable(true);
            mobile.setBookedBy(null);
            mobile.setBookingDate(null);
            mobileDAO.updateMobile(mobile, request.getBookedBy());
            return new BaseResponse(SUCCESS, String.format(MOBILE_RETURNED, request.getModelName()));
        } else {
            return new BaseResponse(NOT_AVAILABLE, String.format(MOBILE_NOT_AVAILABLE, request.getModelName()));
        }
    }
}
