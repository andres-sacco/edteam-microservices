package com.edteam.reservations.dto;

import com.edteam.reservations.validator.CityFormatConstraint;
import jakarta.validation.constraints.NotBlank;

public class SegmentDTO {

    @CityFormatConstraint
    private String origin;

    @CityFormatConstraint
    private String destination;

    @NotBlank(message = "departure is mandatory")
    private String departure;

    @NotBlank(message = "arrival is mandatory")
    private String arrival;

    @NotBlank(message = "carrier is mandatory")
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
