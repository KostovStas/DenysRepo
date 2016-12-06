package com.booking.structure.beans;

public class HotelSearchCriteriaBean {

    private String destination;
    private String checkInDate;
    private String checkOutDate;

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(final String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(final String destination) {
        this.destination = destination;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(final String checkInDate) {
        this.checkInDate = checkInDate;
    }
}
