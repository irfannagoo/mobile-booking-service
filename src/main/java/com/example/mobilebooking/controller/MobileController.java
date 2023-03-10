package com.example.mobilebooking.controller;

import com.example.mobilebooking.request.MobileBookingRequest;
import com.example.mobilebooking.response.MobileBookingResponse;
import com.example.mobilebooking.response.BaseResponse;
import com.example.mobilebooking.service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author irfan.nagoo
 * @created 10-03-2023
 */

@RestController
@RequestMapping("/mobile")
public class MobileController {

    private final MobileService mobileService;

    public MobileController(MobileService mobileService) {
        this.mobileService = mobileService;
    }

    /**
     * Returns the list of all the mobile phones with status
     *
     * @return
     */

    @GetMapping("/all")
    public MobileBookingResponse getMobileAll() {
        return mobileService.getMobileAll();
    }

    /**
     * Returns a particular mobile model
     *
     * @param modelName
     * @return
     */

    @GetMapping("/{modelName}")
    public MobileBookingResponse getMobile(@PathVariable("modelName") String modelName) {
        return mobileService.getMobile(modelName);
    }

    /**
     * Books the mobile against a particular individual
     *
     * @param request
     * @return
     */
    @PutMapping("/book")
    public BaseResponse bookMobile(@Valid @RequestBody MobileBookingRequest request) {
        return mobileService.bookMobile(request);
    }

    /**
     * Returns back the mobile taken by an individual
     *
     * @param request
     * @return
     */
    @PutMapping("/return")
    public BaseResponse returnMobile(@Valid @RequestBody MobileBookingRequest request) {
        return mobileService.returnMobile(request);
    }

}
