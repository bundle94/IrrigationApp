package com.banquemisr.irrigationApp.controller;

import com.banquemisr.irrigationApp.exception.LandNotFoundException;
import com.banquemisr.irrigationApp.model.request.PlotRequest;
import com.banquemisr.irrigationApp.model.request.TimeSlotRequest;
import com.banquemisr.irrigationApp.model.response.BaseResponse;
import com.banquemisr.irrigationApp.model.response.LandResponse;
import com.banquemisr.irrigationApp.service.LandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/plots/")
public class PlotsController {

    private final LandService landService;

    @Autowired
    public PlotsController(LandService landService) {
        this.landService = landService;
    }

    @PostMapping(value = "addPlot")
    public ResponseEntity<BaseResponse<LandResponse>> addPlot(@RequestBody PlotRequest plotRequest){
        return new ResponseEntity<>(landService.addPlot(plotRequest),CREATED);
    }

    @PostMapping("configurePlot")
    public ResponseEntity<BaseResponse<LandResponse>> configurePlot(@RequestBody TimeSlotRequest timeSlotRequest) throws LandNotFoundException{
        return new ResponseEntity<>(landService.configurePlot(timeSlotRequest),OK);
    }

    @PutMapping("editPlot/{plotId}")
    public ResponseEntity<BaseResponse<LandResponse>> updatePlot(@PathVariable Long plotId, @RequestBody PlotRequest plotRequest) throws LandNotFoundException {
        return new ResponseEntity<>(landService.editPlot(plotId,plotRequest),OK);
    }

    @GetMapping(value = "listAllPlots",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<List<LandResponse>>> listAllPlots(){
        return new ResponseEntity<>(landService.listAllPlots(),OK);
    }
}
