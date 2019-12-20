package com.altimetrik.playground.clientservice.controller;


import com.altimetrik.playground.clientservice.IVehicleData;
import com.altimetrik.playground.clientservice.Vehicle;
import com.altimetrik.playground.clientservice.VehicleMake;
import com.altimetrik.playground.clientservice.VehicleModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/frontend")
@Api(value = "API for Frontend End usage", description = "Returns FrontEnd Content")
public class HomeController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private IVehicleData vehicleData;

    private  String uri = "http://localhost:9090/altimetrik/decode/";

    @ApiOperation(value = "Returns The Homepage")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 100, message = "Information"),
                    @ApiResponse(code = 200, message = "Successfully Delivered"),
                    @ApiResponse(code = 404, message = "No Page Found")
            }
    )
    @GetMapping(value = "/home")
    public String home(){

        return "index";
    }
    @ApiOperation(value = "Returns The Vehicle Details for a given VIN Number")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 100, message = "Information"),
                    @ApiResponse(code = 200, message = "Successful"),
                    @ApiResponse(code = 404, message = "No Page Found")
            }
    )
    @GetMapping(value = "/search")
    public String getVehicle(@RequestParam(value = "vinNumber", required = false)  String vinNumber, Model model){

        List<Vehicle> vehicleList = this.vehicleData.decodeVIN(vinNumber);

        model.addAttribute("vehicleList", vehicleList.get(0));
        model.addAttribute("vehicleMakes", vehicleList.get(0).getVehicleMakes());
        model.addAttribute("vehicleModels", vehicleList.get(0).getVehicleModels());

        return "index";
    }
}
