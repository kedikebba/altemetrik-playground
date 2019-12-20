package com.altimetrik.playground.vehicleservice.model;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Model {

    private Integer makeID;
    private String makeName;
    private Integer modelID;
    private String modelName;
}
