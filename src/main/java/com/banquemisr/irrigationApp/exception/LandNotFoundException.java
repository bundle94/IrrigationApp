package com.banquemisr.irrigationApp.exception;

public class LandNotFoundException extends Exception{

    private String status;
    public LandNotFoundException(String status,String message) {
        super(message);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
