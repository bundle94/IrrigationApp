package com.banquemisr.irrigationApp.service.Implementation;

import com.banquemisr.irrigationApp.exception.LandNotFoundException;
import com.banquemisr.irrigationApp.model.entity.Plot;
import com.banquemisr.irrigationApp.model.entity.TimeSlot;
import com.banquemisr.irrigationApp.model.request.PlotRequest;
import com.banquemisr.irrigationApp.model.request.TimeSlotRequest;
import com.banquemisr.irrigationApp.model.response.BaseResponse;
import com.banquemisr.irrigationApp.model.response.LandResponse;
import com.banquemisr.irrigationApp.repository.PlotRepository;
import com.banquemisr.irrigationApp.repository.TimeSlotRepository;
import com.banquemisr.irrigationApp.service.LandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LandServiceImpl implements LandService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LandServiceImpl.class);
    private final PlotRepository plotRepository;
    private final TimeSlotRepository timeSlotRepository;
    public static final String EMPTY_STRING = "";

    @Autowired
    public LandServiceImpl(PlotRepository plotRepository, TimeSlotRepository timeSlotRepository) {
        this.plotRepository = plotRepository;
        this.timeSlotRepository = timeSlotRepository;
    }

    @Override
    public BaseResponse<LandResponse> addPlot(PlotRequest plotRequest) {
        LOGGER.info("Creating a new plot of land");
        Plot plot = new Plot();
        plot.setLastDateIrrigated(plotRequest.getLastDateIrrigated());
        plot.setSize(plotRequest.getSize());
        plot.setSurveyed(plotRequest.isSurveyed());
        plot.setGeoCordinates(plotRequest.getGeoCordinates());
        plot.setCrop(plotRequest.getCrop());
        plotRepository.save(plot);
        return new BaseResponse<>("success", "Plot added successfully",null);
    }

    private Plot checkIfPlotExists(Long id) throws LandNotFoundException {
        Optional<Plot> plot = plotRepository.findById(id);
        if(plot.isEmpty()){
            throw new LandNotFoundException("error", "Plot not found");
        }
        return plot.get();
    }

    @Override
    public BaseResponse<LandResponse> configurePlot(TimeSlotRequest timeSlotRequest) throws LandNotFoundException {
        LOGGER.info(String.format("configuring plot %s ", timeSlotRequest.getPlotId()));
        Plot plot = checkIfPlotExists(timeSlotRequest.getPlotId());
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setTime(Time.valueOf(timeSlotRequest.getTime()));
        timeSlot.setPlotId(timeSlotRequest.getPlotId());
        timeSlot.setAmountOfWaterNeeded(timeSlotRequest.getAmountOfWaterNeeded());
        timeSlotRepository.save(timeSlot);
        return new BaseResponse<>("success", String.format("Slot configured for plot %s with crop %s to be done at %s", timeSlotRequest.getPlotId(), plot.getCrop(), timeSlotRequest.getTime().toString()), null);
    }

    @Override
    public BaseResponse<LandResponse> editPlot(Long id, PlotRequest plotRequest) throws LandNotFoundException {
        LOGGER.info(String.format("Editing plot with ID %s", id));
        Plot plot = checkIfPlotExists(id);
        if(plotRequest.getSize()!= null && !EMPTY_STRING.equals(plotRequest.getSize())) {
            plot.setSize(plotRequest.getSize());
        }
        if(plotRequest.getGeoCordinates()!=null || !EMPTY_STRING.equals(plotRequest.getGeoCordinates())) {
            plot.setGeoCordinates(plotRequest.getGeoCordinates());
        }
        plot.setSurveyed(plotRequest.isSurveyed());
        if(plotRequest.getCrop()!=null || !EMPTY_STRING.equals(plotRequest.getCrop().isEmpty())) {
            plot.setCrop(plotRequest.getCrop());
        }
        plotRepository.save(plot);
        return new BaseResponse("success", "Plot edited successfully",null);
    }

    private LandResponse fetchSlotsAttachedToPlots(Plot plot, List<TimeSlot> timeSlots) {
        LandResponse response = new LandResponse();
        response.setCrop(plot.getCrop());
        response.setSize(plot.getSize());
        response.setGeoCordinates(plot.getGeoCordinates());
        response.setSurveyed(plot.isSurveyed());
        response.setId(plot.getId());
        response.setLastDateIrrigated(plot.getLastDateIrrigated());
        if (timeSlots !=null){
            response.setTimeSlots(timeSlots);
        }
        return response;
    }

    @Override
    public BaseResponse<List<LandResponse>> listAllPlots() {
        LOGGER.info("Fetching all plots together with all the timeslots assigned to them");
        List<LandResponse> plots = plotRepository.findAll().stream().map(plot -> {
            List<TimeSlot> timeSlots = timeSlotRepository.findAllByPlotId(plot.getId());
            return fetchSlotsAttachedToPlots(plot, timeSlots);
        }).collect(Collectors.toList());
        return new BaseResponse<>("success", "plots and slots fetched successfully", plots);
    }

}
