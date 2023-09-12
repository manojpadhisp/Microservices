package com.example.hotel.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String ex) {
        super(ex);
    }

    public ResourceNotFoundException() {
        super("Resource not found exception!!");
    }
}
