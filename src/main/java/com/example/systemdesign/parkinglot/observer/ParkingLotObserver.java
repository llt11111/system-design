package com.example.systemdesign.parkinglot.observer;

import com.example.systemdesign.parkinglot.VehicleType;

public interface ParkingLotObserver {
    void onSpotTaken(int floor, VehicleType type);
    void onSpotFreed(int floor, VehicleType type);
}
