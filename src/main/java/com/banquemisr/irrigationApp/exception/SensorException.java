package com.banquemisr.irrigationApp.exception;

public class SensorException extends RuntimeException{
    private String status;
    public SensorException(String status,String message) {
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
