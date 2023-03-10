package com.example.mobilebooking.request;

import javax.validation.constraints.NotEmpty;

/**
 * @author irfan.nagoo
 * @created 10-03-2023
 */
public class MobileBookingRequest {

    @NotEmpty
    private String modelName;
    @NotEmpty
    private String bookedBy;

    public MobileBookingRequest(String modelName, String bookedBy) {
        this.modelName = modelName;
        this.bookedBy = bookedBy;
    }

    public String getModelName() {
        return modelName;
    }

    public String getBookedBy() {
        return bookedBy;
    }
}
