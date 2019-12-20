package com.altimetrik.playground.vehicleservice.model;


import lombok.*;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Make {

    private Integer makeID;
    private String makeName;
    private Integer vehicleTypeID;
    private String vehicleTypeName;


}
