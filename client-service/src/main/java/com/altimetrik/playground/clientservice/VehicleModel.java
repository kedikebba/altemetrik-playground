package com.altimetrik.playground.clientservice;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VehicleModel {
    private Integer makeID;
    private String makeName;
    private Integer modelID;
    private String modelName;
}
