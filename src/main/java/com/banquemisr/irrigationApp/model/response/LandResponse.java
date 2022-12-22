package com.banquemisr.irrigationApp.model.response;


import com.banquemisr.irrigationApp.model.entity.TimeSlot;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LandResponse {
    private long id;
    private String size;
    private String geoCordinates;
    private boolean surveyed;
    private Date lastDateIrrigated;
    private String crop;
    private List<TimeSlot> timeSlots;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getGeoCordinates() {
        return geoCordinates;
    }

    public void setGeoCordinates(String geoCordinates) {
        this.geoCordinates = geoCordinates;
    }

    public boolean isSurveyed() {
        return surveyed;
    }

    public void setSurveyed(boolean surveyed) {
        this.surveyed = surveyed;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public Date getLastDateIrrigated() {
        return lastDateIrrigated;
    }

    public void setLastDateIrrigated(Date lastDateIrrigated) {
        this.lastDateIrrigated = lastDateIrrigated;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }
}

