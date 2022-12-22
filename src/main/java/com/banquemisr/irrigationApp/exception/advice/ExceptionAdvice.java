package com.banquemisr.irrigationApp.exception.advice;

import com.banquemisr.irrigationApp.exception.LandNotFoundException;
import com.banquemisr.irrigationApp.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(LandNotFoundException.class)
    public ErrorResponse errorResponse(LandNotFoundException landNotFoundException){
        return new ErrorResponse(landNotFoundException.getStatus(),landNotFoundException.getMessage());
    }
}
