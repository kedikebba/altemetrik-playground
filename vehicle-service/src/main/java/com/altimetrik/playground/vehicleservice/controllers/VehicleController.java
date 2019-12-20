package com.altimetrik.playground.vehicleservice.controllers;


import com.altimetrik.playground.vehicleservice.model.Make;
import com.altimetrik.playground.vehicleservice.model.Model;
import com.altimetrik.playground.vehicleservice.model.Vehicle;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/altimetrik")
@Api(value = "API for Backend usage", description = "Returns Calls made on the Backend")
public class VehicleController {

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "Returns The List of Vehicle Makes and Models")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 100, message = "Information"),
                    @ApiResponse(code = 200, message = "Successfully Delivered"),
                    @ApiResponse(code = 404, message = "No Page Found")
            }
    )
    @PostMapping(value = "/decode", produces = "application/json")
    public List<Vehicle> decodeVIN(@RequestBody String vinNumber){

        List<Model> models = new ArrayList<>();
        List<Make> makes = new ArrayList<>();
        String vType;
        String vMake;



        String decodeURI = "https://vpic.nhtsa.dot.gov/api/vehicles/decodevinvalues/"+vinNumber+"?format=json";
        String decodedData = restTemplate.getForObject(decodeURI, String.class);
        JSONObject decodedJSON = new JSONObject(decodedData);
        decodedJSON = decodedJSON.getJSONArray("Results").getJSONObject(0);

        String vehicleVIN = decodedJSON.getString("VIN");
        String vehicleMake = decodedJSON.getString("Make");
        String vehicleModel = decodedJSON.getString("Model");
        String vehicleYear = decodedJSON.getString("ModelYear");
        String vehiclePlantCountry = decodedJSON.getString("PlantCountry");
        String vehiclePlantState = decodedJSON.getString("PlantState");
        String vehicleType = decodedJSON.getString("VehicleType");
        String vehicleCity = decodedJSON.getString("PlantCity");

        vType = vehicleType;
        vMake = vehicleMake;

        String getMakesURI = "https://vpic.nhtsa.dot.gov/api/vehicles/GetMakesForVehicleType/"+vType+"?format=json";
        String makesData = restTemplate.getForObject(getMakesURI, String.class);
        JSONObject makesJSON = new JSONObject(makesData);
        JSONArray makesArray = new JSONArray();
        makesArray = makesJSON.getJSONArray("Results");

        for(int i = 0; i< makesArray.length(); i++){

            JSONObject jsonObject = new JSONObject();
            jsonObject = makesArray.getJSONObject(i);
            Make make = new Make();

            make.setMakeID(jsonObject.getInt("MakeId"));
            make.setMakeName(jsonObject.getString("MakeName"));
            make.setVehicleTypeID(jsonObject.getInt("VehicleTypeId"));
            make.setVehicleTypeName(jsonObject.getString("VehicleTypeName"));
            makes.add(make);
        }



        String getModelsURI = "https://vpic.nhtsa.dot.gov/api/vehicles/GetModelsForMake/"+vMake+"?format=json";
        String modelsData = restTemplate.getForObject(getModelsURI, String.class);
        JSONObject modelsJSON = new JSONObject(modelsData);
        JSONArray modelsArray = new JSONArray();
        modelsArray = modelsJSON.getJSONArray("Results");

        for(int i = 0; i< modelsArray.length(); i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject = modelsArray.getJSONObject(i);

            Model model = new Model();

            model.setMakeID(jsonObject.getInt("Make_ID"));
            model.setMakeName(jsonObject.getString("Make_Name"));
            model.setModelID(jsonObject.getInt("Model_ID"));
            model.setModelName(jsonObject.getString("Model_Name"));

            models.add(model);
        }
        /*
        Create Vehicle Object:
         */

        Vehicle vehicle = new Vehicle();

        vehicle.setVehicleVIN(vehicleVIN);
        vehicle.setVehicleMake(vehicleMake);
        vehicle.setVehicleModel(vehicleModel);
        vehicle.setVehicleYear(vehicleYear);
        vehicle.setVehiclePlantCountry(vehiclePlantCountry);
        vehicle.setVehiclePlantState(vehiclePlantState);
        vehicle.setVehicleType(vehicleType);
        vehicle.setVehiclePlantCity(vehicleCity);

        vehicle.setVehicleMakes(makes);
        vehicle.setVehicleModels(models);

        List<Vehicle> mm = new ArrayList<>();
        mm.add(vehicle);

        return mm;

    }

    @GetMapping("/decode/2C8GP64L070017305")
    public Vehicle decodeTest(){
        String decodeURI = "https://vpic.nhtsa.dot.gov/api/vehicles/decodevinvalues/2C8GP64L070017305?format=json";
        String decodedData = restTemplate.getForObject(decodeURI, String.class);
        JSONObject decodedJSON = new JSONObject(decodedData);
        decodedJSON = decodedJSON.getJSONArray("Results").getJSONObject(0);

        String vehicleVIN = decodedJSON.getString("VIN");
        String vehicleMake = decodedJSON.getString("Make");
        String vehicleModel = decodedJSON.getString("Model");
        String vehicleYear = decodedJSON.getString("ModelYear");
        String vehiclePlantCountry = decodedJSON.getString("PlantCountry");
        String vehiclePlantState = decodedJSON.getString("PlantState");
        String vehicleType = decodedJSON.getString("VehicleType");
        String vehicleCity = decodedJSON.getString("PlantCity");

        Vehicle vehicle = new Vehicle();

        vehicle.setVehicleVIN(vehicleVIN);
        vehicle.setVehicleMake(vehicleMake);
        vehicle.setVehicleModel(vehicleModel);
        vehicle.setVehicleYear(vehicleYear);
        vehicle.setVehiclePlantCountry(vehiclePlantCountry);
        vehicle.setVehiclePlantState(vehiclePlantState);
        vehicle.setVehicleType(vehicleType);
        vehicle.setVehiclePlantCity(vehicleCity);


        return vehicle;
    }

}
