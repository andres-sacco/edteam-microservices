package com.edteam.reservations.model;

import java.util.List;

public class Itinerary {
    private Long id;
    private List<Segment> segment;

    private Price price;

    public List<Segment> getSegment() {
        return segment;
    }

    public void setSegment(List<Segment> segment) {
        this.segment = segment;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
