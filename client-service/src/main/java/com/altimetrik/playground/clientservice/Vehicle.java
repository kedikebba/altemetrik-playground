package com.altimetrik.playground.clientservice;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vehicle {

    private String vehicleVIN;
    private String vehicleMake;
    private String vehicleModel;
    private String vehicleYear;
    private String vehiclePlantCountry;
    private String vehiclePlantState;
    private String vehicleType;
    private String vehiclePlantCity;
   private List<VehicleModel> vehicleModels;
   private List<VehicleMake> vehicleMakes;



}
