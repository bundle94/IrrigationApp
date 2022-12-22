package com.banquemisr.irrigationApp.model.entity;


import javax.persistence.*;
import java.sql.Date;

@Entity
public class Plot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "size")
    private String size;
    @Column(name = "surveyed")
    private boolean surveyed;
    @Column(name = "geo_cordinates")
    private String geoCordinates;
    @Column(name = "last_date_irrigated")
    private Date lastDateIrrigated;
    private String crop;

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
