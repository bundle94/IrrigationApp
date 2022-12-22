package com.banquemisr.irrigationApp.model.request;

import java.sql.Date;

public class PlotRequest {
    private String size;
    private boolean surveyed;
    private String geoCordinates;
    private Date lastDateIrrigated;
    private String crop;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isSurveyed() {
        return surveyed;
    }

    public void setSurveyed(boolean surveyed) {
        this.surveyed = surveyed;
    }

    public String getGeoCordinates() {
        return geoCordinates;
    }

    public void setGeoCordinates(String geoCordinates) {
        this.geoCordinates = geoCordinates;
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
