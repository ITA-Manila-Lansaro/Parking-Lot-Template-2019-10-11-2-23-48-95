package com.thoughtworks.parking_lot.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.Entity.ParkingLot;
import com.thoughtworks.parking_lot.Service.ParkingLotService;
import com.thoughtworks.parking_lot.Service.ParkingOrderService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ParkingLotController.class)
@ActiveProfiles(profiles = "test")
class ParkingLotControllerTest {

    @MockBean
    ParkingLotService parkingLotService;

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    private ParkingLot parkingLot = new ParkingLot();

    @BeforeEach
    void setUp (){
        this.parkingLot.setId("123");
        this.parkingLot.setCapacity(10);
        this.parkingLot.setLocation("Maax");
        this.parkingLot.setName("Parking Lot 1");
    }

    private List<ParkingLot> parkingLotList = new ArrayList<>();

    @Test
    void should_add_parking_lot_successfully() throws Exception {
        ResultActions result = mvc.perform(post("/parkingLot")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(parkingLot)));

        result.andExpect(status().isOk());
    }

    @Test
    void should_get_all_parking_lot() throws Exception {
        parkingLotList.add(parkingLot);
        parkingLotList.add(parkingLot);
        when(parkingLotService.getAllParkingLot()).thenReturn(parkingLotList);
        ResultActions result = mvc.perform(get("/parkingLot"));

        result.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void should_get_specific_parking_lot() throws Exception {
        when(parkingLotService.getParkingLot("Parking Lot 1")).thenReturn(parkingLot);
        ResultActions result = mvc.perform(get("/parkingLot/Parking Lot 1"));

        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is("123")))
                .andExpect(jsonPath("$.name", is("Parking Lot 1")))
                .andExpect(jsonPath("$.capacity", is(10)))
                .andExpect(jsonPath("$.location", is("Maax")));
    }

    @Test
    void should_update_parking_lot_facility() throws Exception {
        parkingLot.setCapacity(15);
        when(parkingLotService.expandCapacity("Parking Lot 1", 15)).thenReturn(parkingLot);

        ResultActions result = mvc.perform(patch("/parkingLot/Parking Lot 1/15"));

        result.andExpect(status().isOk());
    }
}