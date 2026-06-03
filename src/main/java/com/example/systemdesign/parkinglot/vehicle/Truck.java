package com.example.systemdesign.parkinglot.vehicle;

import com.example.systemdesign.parkinglot.VehicleType;

public class Truck extends Vehicle {
    public Truck(String vehicleNumber) {
        super(vehicleNumber, VehicleType.TRUCK);
    }
}
