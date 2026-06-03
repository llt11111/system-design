package com.example.systemdesign.parkinglot.vehicle;

import com.example.systemdesign.parkinglot.VehicleType;

public abstract class Vehicle {
    private final String vehicleNumber;
    private final VehicleType type;

    public Vehicle(String vehicleNumber, VehicleType type) {
        this.vehicleNumber = vehicleNumber;
        this.type = type;
    }

    public String getVehicleNumber() { return vehicleNumber; }
    public VehicleType getType() { return type; }
}
