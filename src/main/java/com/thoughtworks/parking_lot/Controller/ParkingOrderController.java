package com.thoughtworks.parking_lot.Controller;

import com.thoughtworks.parking_lot.Entity.ParkingLot;
import com.thoughtworks.parking_lot.Entity.ParkingOrder;
import com.thoughtworks.parking_lot.Service.ParkingLotService;
import com.thoughtworks.parking_lot.Service.ParkingOrderService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parkingOrder")
public class ParkingOrderController {
    public static final String PARKING_LOT_NOT_FOUND = "Parking Lot Not found";
    @Autowired
    ParkingLotService parkingLotService;

    @Autowired
    ParkingOrderService parkingOrderService;

    @PostMapping(value = "/{parkingName}", produces = {"application/json"})
    public ParkingOrder createOrder(@PathVariable String parkingName,
                                  @RequestParam ParkingOrder parkingOrder) throws NotFoundException {

        ParkingLot parkingLot = parkingLotService.getParkingLot(parkingName);

        if (parkingLot != null) {
            return parkingOrderService.createParkingOrder(parkingLot, parkingOrder);
        }
        throw new NotFoundException(PARKING_LOT_NOT_FOUND);
    }

    @PatchMapping(value = "/{plateNumber}", produces = {"application/json"})
    public ParkingOrder checkOut (@PathVariable String plateNumber) throws NotFoundException {

        ParkingOrder parkingOrder = parkingOrderService.checkOutCar(plateNumber);

        if(parkingOrder != null){
            return parkingOrder;
        }
        throw new NotFoundException(PARKING_LOT_NOT_FOUND);
    }
}
