package com.b902.watersupply;

import org.springframework.http.ResponseEntity;


public class UnableToProceedRequestException extends RuntimeException {

    private String resourceName;
    private String message;

    public UnableToProceedRequestException(String resourceName , String message) {
        super(message);
        this.resourceName = resourceName;
        this.message = message;
    }

    public UnableToProceedRequestException(String message) {
        super(message);
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
