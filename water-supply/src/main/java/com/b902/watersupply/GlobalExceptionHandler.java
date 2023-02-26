package com.b902.watersupply;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.http.HttpResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnableToProceedRequestException.class)
    public ResponseEntity<?> unableToProceedRequestHandler(UnableToProceedRequestException ex){
            String message = ex.getMessage();

            ApiResponse apiResponse = new ApiResponse(message, "FAILURE");
            return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFound(UnableToProceedRequestException ex){
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, "FAILURE");
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
    }
}
