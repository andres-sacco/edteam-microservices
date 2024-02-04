package com.edteam.reservations.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SegmentDTO {

    @NotBlank(message = "origin is mandatory")
    @Size(min = 3, max = 3, message = "The origin must have 3 characters")
    private String origin;

    @NotBlank(message = "destination is mandatory")
    @Size(min = 3, max = 3, message = "The destination must have 3 characters")
    private String destination;

    private String departure;

    private String arrival;

    private String carrier;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
}
