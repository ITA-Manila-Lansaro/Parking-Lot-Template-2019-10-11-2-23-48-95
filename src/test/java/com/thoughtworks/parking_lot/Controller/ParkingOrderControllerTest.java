package com.thoughtworks.parking_lot.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.Entity.ParkingLot;
import com.thoughtworks.parking_lot.Entity.ParkingOrder;
import com.thoughtworks.parking_lot.Service.ParkingLotService;
import com.thoughtworks.parking_lot.Service.ParkingOrderService;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ParkingOrderController.class)
@ActiveProfiles(profiles = "test")
class ParkingOrderControllerTest {

    @MockBean
    ParkingOrderService parkingOrderService;

    @MockBean
    ParkingLotService parkingLotService;

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    private ParkingLot parkingLot = new ParkingLot();

    private ParkingOrder parkingOrder = new ParkingOrder();

    @BeforeEach
    void setUp (){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        this.parkingLot.setId("123");
        this.parkingLot.setCapacity(10);
        this.parkingLot.setLocation("Maax");
        this.parkingLot.setName("Parking Lot 1");

        this.parkingOrder.setPlateNumber("KTX123");
        this.parkingOrder.setParkingLot(parkingLot);
        this.parkingOrder.setOrderNumber("1");
        this.parkingOrder.setCreationTime(dateFormat.format(new Date()));
    }

    @Test
    void should_return_Parking_Order_when_parkingLot_and_plate_number_is_not_null() throws Exception {

        when(parkingLotService.getParkingLot("Parking Lot 1")).thenReturn(parkingLot);
        when(parkingOrderService.createParkingOrder(parkingLot, parkingOrder)).thenReturn(parkingOrder);


        ResultActions result = mvc.perform(post("/parkingOrder/Parking Lot 1")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(parkingOrder)));

        result.andExpect(status().isOk());
    }

    @Test
    void should_return_exception_when_parking_lot_is_full() throws Exception {

        when(parkingLotService.getParkingLot("Parking Lot 1")).thenReturn(parkingLot);
        when(parkingOrderService.createParkingOrder(parkingLot, parkingOrder)).thenReturn(parkingOrder);
        when(parkingOrderService.isFull(parkingLot)).thenReturn(true);

        ResultActions result = mvc.perform(post("/parkingOrder/Parking Lot 1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(parkingOrder)));

        result.andExpect(status().isBadRequest());
    }

    @Test
    void should_checkout_when_plate_number_exist() throws Exception {
        when(parkingLotService.getParkingLot("Parking Lot 1")).thenReturn(parkingLot);
        when(parkingOrderService.checkOutCar("KTX123")).thenReturn(parkingOrder);

        ResultActions result = mvc.perform(patch("/parkingOrder/KTX123"));

        result.andExpect(status().isOk());
    }
}