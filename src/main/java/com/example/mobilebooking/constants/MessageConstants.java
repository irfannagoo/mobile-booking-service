package com.example.mobilebooking.constants;

/**
 * @author irfan.nagoo
 * @created 10-03-2023
 */
public interface MessageConstants {

    String TOTAL_RECORD = "Total records[%d] found";
    String MOBILE_NOT_FOUND  = "Mobile [%s] Not Found";
    String MOBILE_BOOKED = "Mobile [%s] Successfully Booked";
    String MOBILE_RETURNED = "Mobile [%s] Successfully Returned";
    String MOBILE_NOT_AVAILABLE = "Mobile [%s] Not Available";

    String APPLICATION_ERROR = "Application Error occurred while processing the request";
    String INVALID_REQUEST_ERROR = "Request attributes cannot be null or empty";
    String GENERAL_ERROR = "General Error occurred while processing the request";
}
