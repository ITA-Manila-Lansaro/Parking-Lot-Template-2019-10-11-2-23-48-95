package com.thoughtworks.parking_lot.Controller;

import com.thoughtworks.parking_lot.Entity.ParkingLot;
import com.thoughtworks.parking_lot.Entity.ParkingOrder;
import com.thoughtworks.parking_lot.Service.ParkingLotService;
import com.thoughtworks.parking_lot.Service.ParkingOrderService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parkingOrder")
public class ParkingOrderController {
    private static final String PARKING_LOT_NOT_FOUND = "Parking Lot Not found";
    private static final String PARKING_LOT_IS_FULL = "Parking Lot is full";
    public static final String PLATE_NUMBER_NOT_FOUND = "Plate Number Not Found";

    private final ParkingLotService parkingLotService;

    private final ParkingOrderService parkingOrderService;

    public ParkingOrderController(ParkingLotService parkingLotService, ParkingOrderService parkingOrderService) {
        this.parkingLotService = parkingLotService;
        this.parkingOrderService = parkingOrderService;
    }

    @PostMapping(value = "/{parkingName}", produces = {"application/json"})
    public ParkingOrder createOrder(@PathVariable String parkingName,
                                    @RequestBody ParkingOrder parkingOrder) throws NotFoundException, InterruptedException {

        ParkingLot parkingLot = parkingLotService.getParkingLot(parkingName);

        if (parkingLot != null) {
            if (!parkingOrderService.isFull(parkingLot))
                return parkingOrderService.createParkingOrder(parkingLot, parkingOrder);
            throw new InterruptedException(PARKING_LOT_IS_FULL);
        }
        throw new NotFoundException(PARKING_LOT_NOT_FOUND);
    }

    @PatchMapping(value = "/{plateNumber}", produces = {"application/json"})
    public ParkingOrder checkOut(@PathVariable String plateNumber) throws NotFoundException {

        ParkingOrder parkingOrder = parkingOrderService.checkOutCar(plateNumber);

        if (parkingOrder != null) {
            return parkingOrder;
        }
        throw new NotFoundException(PLATE_NUMBER_NOT_FOUND);
    }
}
