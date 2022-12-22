package com.banquemisr.irrigationApp.repository;

import com.banquemisr.irrigationApp.model.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Time;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot,Long> {
    List<TimeSlot> findAllByPlotId(Long plotId);

    List<TimeSlot> findAllByTimeBetween(Time startTime, Time endTime);
}
