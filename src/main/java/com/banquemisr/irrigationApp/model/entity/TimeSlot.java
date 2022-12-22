package com.banquemisr.irrigationApp.model.entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "plot_id")
    private long plotId;
    @Column(name = "amount_of_water_needed")
    private double amountOfWaterNeeded;
    private String status;
    @Column(name = "time")
    private Time time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPlotId() {
        return plotId;
    }

    public void setPlotId(long plotId) {
        this.plotId = plotId;
    }

    public double getAmountOfWaterNeeded() {
        return amountOfWaterNeeded;
    }

    public void setAmountOfWaterNeeded(double amountOfWaterNeeded) {
        this.amountOfWaterNeeded = amountOfWaterNeeded;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
