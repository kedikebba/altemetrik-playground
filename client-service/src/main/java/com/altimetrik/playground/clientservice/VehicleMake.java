package com.altimetrik.playground.clientservice;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VehicleMake {

    private Integer makeID;
    private String makeName;
    private Integer vehicleTypeID;
    private String vehicleTypeName;

}
