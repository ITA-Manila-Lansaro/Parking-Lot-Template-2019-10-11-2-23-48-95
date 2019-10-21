package com.thoughtworks.parking_lot.Controller;

import com.thoughtworks.parking_lot.Entity.ParkingLot;
import com.thoughtworks.parking_lot.Service.ParkingLotService;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/parkingLot")
public class ParkingLotController {

    public static final String PARKING_LOT_NOT_FOUND = "Parking Lot Not Found";

    @Autowired
    ParkingLotService parkingLotService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity addParkingLot (@RequestBody ParkingLot parkingLot) throws NotFoundException {
        if (parkingLot != null){
            parkingLotService.addParkingLot(parkingLot);
            return new ResponseEntity(HttpStatus.OK);
        }
        throw new NotFoundException(PARKING_LOT_NOT_FOUND);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ParkingLot> getAllParkingLot (){
        return parkingLotService.getAllParkingLot();
    }

    @GetMapping(value = "/{name}", produces =  MediaType.APPLICATION_JSON_VALUE)
    public ParkingLot getParkingLot (@PathVariable String name) throws NotFoundException {
        if (name !=null ){
            return parkingLotService.getParkingLot(name);
        }
        throw new NotFoundException(PARKING_LOT_NOT_FOUND);
    }

    @PatchMapping(value = "/{name}/{newCapacity}", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity updateCapacity (@PathVariable String name,
                                          @PathVariable Integer newCapacity) throws NotFoundException {
        ParkingLot parkingLot = parkingLotService.expandCapacity(name, newCapacity);
        if (parkingLot != null){
            return new ResponseEntity(HttpStatus.OK);
        }
        throw new NotFoundException(PARKING_LOT_NOT_FOUND);
    }
}
