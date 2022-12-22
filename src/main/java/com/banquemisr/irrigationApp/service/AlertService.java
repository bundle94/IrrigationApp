package com.banquemisr.irrigationApp.service;

public interface AlertService {

    void sendAlert(String email, String title, String message);
}
