package com.banquemisr.irrigationApp.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

public class TimeSlotRequest {
    private long plotId;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime time;
    private double amountOfWaterNeeded;
    private String status;

    public long getPlotId() {
        return plotId;
    }

    public void setPlotId(long plotId) {
        this.plotId = plotId;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public double getAmountOfWaterNeeded() {
        return amountOfWaterNeeded;
    }

    public void setAmountOfWaterNeeded(double amountOfWaterNeeded) {
        this.amountOfWaterNeeded = amountOfWaterNeeded;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
