package com.thoughtworks.parking_lot.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ParkingLot {

    @Id
    @GeneratedValue(generator =  "uuid")
    @GenericGenerator(name = "uuid", strategy =  "uuid")
    private String id;
    private String name;
    private int capacity;
    private String location;

    @OneToMany
    private ParkingOrder parkingOrder;

    public ParkingOrder getParkingOrder() {
        return parkingOrder;
    }

    public void setParkingOrder(ParkingOrder parkingOrder) {
        this.parkingOrder = parkingOrder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
