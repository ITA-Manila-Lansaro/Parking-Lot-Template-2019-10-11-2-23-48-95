package com.thoughtworks.parking_lot.Repositoy;

import com.thoughtworks.parking_lot.Entity.ParkingLot;
import com.thoughtworks.parking_lot.Entity.ParkingOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParkingOrderRepo extends JpaRepository<ParkingOrder, String> {

    ParkingOrder findParkingOderByPlateNumber(String plateNumber);

    @Query("SELECT p.parkingLot FROM ParkingOrder p WHERE p.parkingLot.id = :parkingLotId")
    List<ParkingLot> lookAllParkingLotById(@Param("parkingLotId") String parkingLotId);
}
