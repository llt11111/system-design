package com.example.systemdesign.parkinglot.vehicle;

import com.example.systemdesign.parkinglot.VehicleType;

public class Bike extends Vehicle {
    public Bike(String vehicleNumber) {
        super(vehicleNumber, VehicleType.BIKE);
    }
}
