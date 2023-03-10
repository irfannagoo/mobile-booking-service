package com.example.mobilebooking.controller;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class MobileControllerTest {

    @InjectMocks
    private MobileController controller;

    @Mock
    private MobileService service;

    @Test
    void getMobileAll() {
        MobileBookingResponse mockResponse = new MobileBookingResponse("TestCode", "TestMessage");
        mockResponse.getMobileList().add(new Mobile(10l, "Samsung S10"));
        when(service.getMobileAll()).thenReturn(mockResponse);
        MobileBookingResponse response = controller.getMobileAll();
        assertEquals(response.getMobileList().size(), 1);
        assertEquals("Samsung S10", response.getMobileList().get(0).getModelName());
    }

    @Test
    void getMobile() {
        MobileBookingResponse mockResponse = new MobileBookingResponse("TestCode", "TestMessage");
        mockResponse.getMobileList().add(new Mobile(10l, "Samsung S10"));
        when(service.getMobile(anyString())).thenReturn(mockResponse);
        MobileBookingResponse response = controller.getMobile("Samsung S10");
        assertEquals(response.getMobileList().size(), 1);
        assertEquals("Samsung S10", response.getMobileList().get(0).getModelName());
    }

    @Test
    void bookMobile() {
        BaseResponse mockResponse = new BaseResponse("TestCode", "TestMessage");
        MobileBookingRequest request = new MobileBookingRequest("Nokia N10", "TestUser");
        when(service.bookMobile(any())).thenReturn(mockResponse);
        BaseResponse response = controller.bookMobile(request);
        assertEquals("TestCode", response.getCode());
    }

    @Test
    void returnMobile() {
        BaseResponse mockResponse = new BaseResponse("TestCode", "TestMessage");
        MobileBookingRequest request = new MobileBookingRequest("Nokia N10", "TestUser");
        when(service.returnMobile(any())).thenReturn(mockResponse);
        BaseResponse response = controller.returnMobile(request);
        assertEquals("TestCode", response.getCode());
    }
}