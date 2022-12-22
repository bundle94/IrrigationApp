package com.banquemisr.irrigationApp;


import com.banquemisr.irrigationApp.exception.LandNotFoundException;
import com.banquemisr.irrigationApp.model.entity.Plot;
import com.banquemisr.irrigationApp.model.request.PlotRequest;
import com.banquemisr.irrigationApp.model.request.TimeSlotRequest;
import com.banquemisr.irrigationApp.model.response.BaseResponse;
import com.banquemisr.irrigationApp.model.response.LandResponse;
import com.banquemisr.irrigationApp.repository.PlotRepository;
import com.banquemisr.irrigationApp.repository.TimeSlotRepository;
import com.banquemisr.irrigationApp.service.LandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LandServiceTest {

    @Autowired
    private LandService landService;
    @Autowired
    private PlotRepository plotRepository;
    @Autowired
    private TimeSlotRepository timeSlotRepository;


    @Test
    void addPlot() {
        PlotRequest plot = new PlotRequest();
        plot.setGeoCordinates("-345677, 9083745");
        plot.setSize("7696896sqm");
        plot.setSurveyed(true);
        plot.setCrop("Pumpkin");
        BaseResponse<LandResponse> result = landService.addPlot(plot);
        assertNotNull(result);
        assertEquals(result.getStatus(), "success");
    }

    @Test
    void configurePlot() throws LandNotFoundException {
        TimeSlotRequest request = new TimeSlotRequest();
        request.setPlotId(1);
        request.setTime(LocalTime.now());
        request.setAmountOfWaterNeeded(30);
        BaseResponse<LandResponse> result = landService.configurePlot(request);
        assertNotNull(result);
        assertEquals(result.getStatus(), "success");
    }

    @Test
    void editPlot() throws LandNotFoundException {
        Plot plot = new Plot();
        plot.setGeoCordinates("-345677, 9083745");
        plot.setSize("7696896sqm");
        plot.setSurveyed(true);
        plot.setCrop("Pumpkin");
        long plotId = plotRepository.save(plot).getId();

        PlotRequest request = new PlotRequest();
        request.setCrop("Water Mellon");

        BaseResponse<LandResponse> res = landService.editPlot(plotId, request);
        Optional<Plot> plotById = plotRepository.findById(plotId);

        assertNotNull(res);
        assertEquals(plotById.get().getCrop(), "Water Mellon");
    }

    @Test
    void listAllPlots() {
        BaseResponse<List<LandResponse>> result = landService.listAllPlots();
        assertTrue(result.getData().size()>0);
        LandResponse plot = result.getData().get(0);
        assertTrue(plot.getTimeSlots().size()>0);
    }
}
