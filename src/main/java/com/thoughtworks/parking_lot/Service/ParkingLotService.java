package com.thoughtworks.parking_lot.Service;

import com.thoughtworks.parking_lot.Entity.ParkingLot;
import com.thoughtworks.parking_lot.Repositoy.ParkingLotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {

    @Autowired
    ParkingLotRepo parkingLotRepo;

    public void addParkingLot (ParkingLot parkingLot){
        parkingLotRepo.save(parkingLot);
    }

    public List<ParkingLot> getAllParkingLot() {
        return parkingLotRepo.findAll();
    }

    public ParkingLot getParkingLot(String name) {
        return parkingLotRepo.findParkingLotByName(name);
    }

    public ParkingLot expandCapacity(String name, Integer newCapacity) {
        ParkingLot parkingLot = parkingLotRepo.findParkingLotByName(name);
        if (parkingLot != null){
            parkingLot.setCapacity(newCapacity);
            parkingLotRepo.save(parkingLot);
            return parkingLot;
        }
        return null;
    }
}
