package com.thoughtworks.parking_lot.Service;

import com.thoughtworks.parking_lot.Entity.ParkingLot;
import com.thoughtworks.parking_lot.Entity.ParkingOrder;
import com.thoughtworks.parking_lot.Repositoy.ParkingOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParkingOrderService {

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


    @Autowired
    ParkingOrderRepo parkingOrderRepo;

    public String createNewDateFormat (Date date){
        return dateFormat.format(date);
    }

    public ParkingOrder createParkingOrder(ParkingLot parkingLot, ParkingOrder parkingOrder) {
        parkingOrder.setParkingLot(parkingLot);
        parkingOrder.setCreationTime(createNewDateFormat(new Date()));
        parkingOrderRepo.save(parkingOrder);
        return parkingOrder;
    }

    public ParkingOrder checkOutCar(String plateNumber) {
        ParkingOrder parkingOrder = parkingOrderRepo.findParkingOderByPlateNumber(plateNumber);
        parkingOrder.setCloseTime(createNewDateFormat(new Date()));
        parkingOrder.setOrderStatus("Close");
        parkingOrderRepo.save(parkingOrder);
        return parkingOrder;
    }
}
