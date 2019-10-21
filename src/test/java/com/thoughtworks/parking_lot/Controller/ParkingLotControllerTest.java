package com.thoughtworks.parking_lot.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.Entity.ParkingLot;
import com.thoughtworks.parking_lot.Service.ParkingLotService;
import com.thoughtworks.parking_lot.Service.ParkingOrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ParkingLotController.class)
@ActiveProfiles(profiles = "test")
class ParkingLotControllerTest {

    @MockBean
    private ParkingLotService parkingLotService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private ParkingLot parkingLot;

    @Test
    void addParkingLot() throws Exception {
//        parkingLot.setId("123");
//        parkingLot.setCapacity(10);
//        parkingLot.setLocation();
//
//        when(parkingLotService.addParkingLot()).thenReturn();
//
//        ResultActions result = mvc.perform(get("/companies/all"));
//        //then
//        result.andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getAllParkingLot() {
    }

    @Test
    void getParkingLot() {
    }

    @Test
    void updateCapacity() {
    }
}