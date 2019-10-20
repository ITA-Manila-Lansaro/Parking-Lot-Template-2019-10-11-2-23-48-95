package com.thoughtworks.parking_lot.Repositoy;

import com.thoughtworks.parking_lot.Entity.ParkingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingOrderRepo extends JpaRepository<ParkingOrder, String> {

    ParkingOrder findParkingOderByPlateNumber(String plateNumber);
}
