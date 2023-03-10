package com.example.mobilebooking.exception;

import com.example.mobilebooking.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.example.mobilebooking.constants.MessageConstants.*;
import static com.example.mobilebooking.constants.MobileBookingConstants.ERROR;

/**
 * @author irfan.nagoo
 * @created 10-03-2023
 */

@ControllerAdvice
public class MobileBookingControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(MobileBookingControllerAdvice.class);

    @ExceptionHandler(MobileBookingException.class)
    public ResponseEntity<BaseResponse> handleMobileBookingException(MobileBookingException mbe) {
        LOGGER.error("Error occurred while processing the request", mbe);
        return ResponseEntity.internalServerError()
                .body(new BaseResponse(ERROR, APPLICATION_ERROR));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> handleValidationException(MethodArgumentNotValidException mve) {
        LOGGER.error("Error occurred while processing the request", mve);
        return ResponseEntity.badRequest().body(new BaseResponse(ERROR, INVALID_REQUEST_ERROR));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> handleException(Exception e) {
        LOGGER.error("Error occurred while processing the request", e);
        return ResponseEntity.internalServerError()
                .body(new BaseResponse(ERROR, GENERAL_ERROR));
    }

}
