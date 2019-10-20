package com.thoughtworks.parking_lot.Controller;

import com.thoughtworks.parking_lot.Entity.ParkingLot;
import com.thoughtworks.parking_lot.Service.ParkingLotService;

import javassist.NotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parkingLot")
public class ParkingLotController {

    public static final String PARKING_LOT_NOT_FOUND = "Parking Lot Not Found";
    private ParkingLotService parkingLotService;

    @PostMapping(produces = {"application/json"})
    public HttpEntity addParkingLot (@RequestBody ParkingLot parkingLot) throws NotFoundException {
        if (parkingLot != null){
            parkingLotService.addParkingLot(parkingLot);
            return new ResponseEntity(HttpStatus.OK);
        }
        throw new NotFoundException(PARKING_LOT_NOT_FOUND);
    }

}
