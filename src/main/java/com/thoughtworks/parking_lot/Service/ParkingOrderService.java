package com.thoughtworks.parking_lot.Service;

import com.thoughtworks.parking_lot.Entity.ParkingLot;
import com.thoughtworks.parking_lot.Entity.ParkingOrder;
import com.thoughtworks.parking_lot.Repositoy.ParkingOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ParkingOrderService {

    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private final ParkingOrderRepo parkingOrderRepo;

    public ParkingOrderService(ParkingOrderRepo parkingOrderRepo) {
        this.parkingOrderRepo = parkingOrderRepo;
    }

    private String createNewDateFormat(Date date){
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
        if (parkingOrder != null) {
            parkingOrder.setCloseTime(createNewDateFormat(new Date()));
            parkingOrder.setOrderStatus("Close");
            parkingOrderRepo.save(parkingOrder);
            return parkingOrder;
        }
        return null;
    }

    public boolean isFull(ParkingLot parkingLot) {
        List<ParkingLot> parkingLotList = parkingOrderRepo.lookAllParkingLotById(parkingLot.getId());
        return parkingLotList.size() >= parkingLot.getCapacity();
    }
}
