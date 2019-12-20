package com.altimetrik.playground.vehicleservice;

import com.altimetrik.playground.vehicleservice.VehicleServiceApplication;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = VehicleServiceApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")

public class VehicleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testJSON() throws Exception {

        mockMvc.perform(get("/decode/2C8GP64L070017305")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath(("$.id"), Matchers.is(289)))
                .andExpect(jsonPath(("$.countryCode"), Matchers.is("US")))
                .andExpect(jsonPath(("$.countryName"), Matchers.is("United States")))
                .andExpect(jsonPath(("$.countryRegion"), Matchers.is("")))
                .andExpect(jsonPath(("$.countryLending"), Matchers.is("Not classified")))
                .andExpect(jsonPath(("$.countryIncome"), Matchers.is("High income")))
                .andExpect(jsonPath(("$.countryLat"), Matchers.is("38.8895")))
                .andExpect(jsonPath(("$.countryLong"), Matchers.is("-77.032")));
    }

}