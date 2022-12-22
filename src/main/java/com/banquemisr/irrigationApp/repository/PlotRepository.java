package com.banquemisr.irrigationApp.repository;

import com.banquemisr.irrigationApp.model.entity.Plot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlotRepository extends JpaRepository<Plot,Long> {
}
