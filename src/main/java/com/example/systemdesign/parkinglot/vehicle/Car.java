package com.example.systemdesign.parkinglot.vehicle;

import com.example.systemdesign.parkinglot.VehicleType;

public class Car extends Vehicle {
    public Car(String vehicleNumber) {
        super(vehicleNumber, VehicleType.CAR);
    }
}
