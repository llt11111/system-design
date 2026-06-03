package com.example.systemdesign.parkinglot.factory;

import com.example.systemdesign.parkinglot.VehicleType;
import com.example.systemdesign.parkinglot.vehicle.*;

public class VehicleFactory {
    public static Vehicle createVehicle(VehicleType type, String vehicleNumber) {
        switch (type) {
            case BIKE:  return new Bike(vehicleNumber);
            case CAR:   return new Car(vehicleNumber);
            case TRUCK: return new Truck(vehicleNumber);
            default: throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }
    }
}
