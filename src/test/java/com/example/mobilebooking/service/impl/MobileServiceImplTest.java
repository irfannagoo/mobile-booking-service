package com.example.mobilebooking.service.impl;

import com.example.mobilebooking.controller.MobileController;
import com.example.mobilebooking.dao.MobileDAO;
import com.example.mobilebooking.domain.Mobile;
import com.example.mobilebooking.request.MobileBookingRequest;
import com.example.mobilebooking.response.BaseResponse;
import com.example.mobilebooking.response.MobileBookingResponse;
import com.example.mobilebooking.service.MobileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MobileServiceImplTest {

    @InjectMocks
    private MobileServiceImpl service;

    @Mock
    private MobileDAO mobileDAO;

    @Test
    void getMobileAll() {
        when(mobileDAO.getMobileAll()).thenReturn(Arrays.asList(new Mobile(10l, "Samsung S10")));
        MobileBookingResponse response = service.getMobileAll();
        assertEquals(1, response.getMobileList().size());
        assertEquals("Samsung S10", response.getMobileList().get(0).getModelName());
    }

    @Test
    void getMobile() {
        when(mobileDAO.getMobile(anyString())).thenReturn(Arrays.asList(new Mobile(10l, "Samsung S10")));
        MobileBookingResponse response = service.getMobile("Samsung S10");
        assertEquals(1, response.getMobileList().size());
        assertEquals("Samsung S10", response.getMobileList().get(0).getModelName());
    }

    @Test
    void bookMobile_success() {
        when(mobileDAO.getMobile(anyString())).thenReturn(Arrays.asList(new Mobile(10l, "Samsung S10",
                true, null, null)));
        MobileBookingRequest request = new MobileBookingRequest("Samsung S10", "TestUser");
        BaseResponse response = service.bookMobile(request);
        assertEquals("SUCCESS", response.getCode());
    }

    @Test
    void bookMobile_error() {
        when(mobileDAO.getMobile(anyString())).thenReturn(Arrays.asList(new Mobile(10l, "Nokia N10",
                false, null, null)));
        MobileBookingRequest request = new MobileBookingRequest("Nokia N10", "TestUser");
        BaseResponse response = service.bookMobile(request);
        assertEquals("NOT_AVAILABLE", response.getCode());
    }

    @Test
    void returnMobile_success() {
        when(mobileDAO.getMobile(anyString())).thenReturn(Arrays.asList(new Mobile(10l, "Samsung S10",
                false, "TestUser", null)));
        MobileBookingRequest request = new MobileBookingRequest("Samsung S10", "TestUser");
        BaseResponse response = service.returnMobile(request);
        assertEquals("SUCCESS", response.getCode());
    }

    @Test
    void returnMobile_error() {
        when(mobileDAO.getMobile(anyString())).thenReturn(Arrays.asList(new Mobile(10l, "Nokia N10",
                true, null, null)));
        MobileBookingRequest request = new MobileBookingRequest("Nokia N10", "TestUser");
        BaseResponse response = service.returnMobile(request);
        assertEquals("NOT_AVAILABLE", response.getCode());
    }
}