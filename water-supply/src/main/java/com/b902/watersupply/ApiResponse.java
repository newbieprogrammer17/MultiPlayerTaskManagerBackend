package com.b902.watersupply;

public class ApiResponse {

    String message;
    String success;

    public ApiResponse(String message, String success) {
        this.message = message;
        this.success = success;
    }

    public ApiResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
