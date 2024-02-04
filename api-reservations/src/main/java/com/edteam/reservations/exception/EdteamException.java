package com.edteam.reservations.exception;

public class EdteamException extends RuntimeException {
    private String description;

    public EdteamException(String message) {
        super(message);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
