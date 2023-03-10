package com.example.mobilebooking.exception;

/**
 * @author irfan.nagoo
 * @created 10-03-2023
 */
public class MobileBookingException extends RuntimeException {

    public MobileBookingException(String errorMsg, Throwable e) {
        super(errorMsg, e);
    }
}
