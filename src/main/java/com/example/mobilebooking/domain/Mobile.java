package com.example.mobilebooking.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

/**
 * @author irfan.nagoo
 * @created 10-03-2023
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mobile {

    private final Long id;
    private final String modelName;
    private boolean available;
    private String bookedBy;
    private LocalDate bookingDate;

    public Mobile(Long id, String modelName) {
        this.id = id;
        this.modelName = modelName;
    }

    public Mobile(Long id, String modelName, boolean available, String bookedBy, LocalDate bookingDate) {
        this.id = id;
        this.modelName = modelName;
        this.available = available;
        this.bookedBy = bookedBy;
        this.bookingDate = bookingDate;
    }

    public Long getId() {
        return id;
    }

    public String getModelName() {
        return modelName;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getBookedBy() {
        return bookedBy;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setBookedBy(String bookedBy) {
        this.bookedBy = bookedBy;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(id).append(",").append(modelName).append(",")
                .append(available).append(",")
                .append(null != bookedBy ? bookedBy : "null").append(",")
                .append(null != bookingDate ? bookingDate : "null").toString();
    }
}
