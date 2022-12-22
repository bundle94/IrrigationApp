package com.banquemisr.irrigationApp.service;

import com.banquemisr.irrigationApp.exception.LandNotFoundException;
import com.banquemisr.irrigationApp.model.request.PlotRequest;
import com.banquemisr.irrigationApp.model.request.TimeSlotRequest;
import com.banquemisr.irrigationApp.model.response.BaseResponse;
import com.banquemisr.irrigationApp.model.response.LandResponse;

import java.util.List;

public interface LandService {
    BaseResponse<LandResponse> addPlot(PlotRequest plotRequest);

    BaseResponse<LandResponse> configurePlot(TimeSlotRequest timeSlotRequest) throws LandNotFoundException;

    BaseResponse<LandResponse> editPlot(Long plotId,PlotRequest plotRequest) throws LandNotFoundException;

    BaseResponse<List<LandResponse>> listAllPlots();
}