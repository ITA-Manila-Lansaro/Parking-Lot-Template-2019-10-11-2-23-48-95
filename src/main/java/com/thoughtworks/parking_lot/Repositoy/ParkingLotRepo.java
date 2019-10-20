package com.thoughtworks.parking_lot.Repositoy;

import com.thoughtworks.parking_lot.Entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ParkingLotRepo extends JpaRepository<ParkingLot, String> {
    ParkingLot findParkingLotByName(String name);
}
