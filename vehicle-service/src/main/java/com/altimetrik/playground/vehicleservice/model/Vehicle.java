package com.altimetrik.playground.vehicleservice.model;


import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
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
    private List<Model> vehicleModels;
    private List<Make> vehicleMakes;



}
