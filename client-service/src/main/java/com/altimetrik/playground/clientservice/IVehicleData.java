package com.altimetrik.playground.clientservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "vehicles", url = "http://localhost:9090/altimetrik")
public interface IVehicleData {
    @PostMapping(value = "/decode", produces = "application/json")
    List<Vehicle> decodeVIN(@RequestBody String vinNumber);

}
