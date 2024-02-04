package com.edteam.reservations.enums;

import org.springframework.http.HttpStatus;

public enum APIError {

    VALIDATION_ERROR(HttpStatus.BAD_REQUEST,"The are attributes with wrong values"),
    BAD_FORMAT(HttpStatus.BAD_REQUEST,"The message not have a correct form"),
    RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "Reservation not found"),
    RESERVATION_WITH_SAME_ID(HttpStatus.BAD_REQUEST, "There is a reservation with the same id");

    private final HttpStatus httpStatus;
    private final String message;

    APIError(HttpStatus httpStatus, String message){
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
