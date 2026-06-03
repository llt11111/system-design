package com.example.systemdesign.parkinglot;

import com.example.systemdesign.parkinglot.vehicle.Vehicle;

public class ParkingSpot {
    private final int spotNumber;
    private final VehicleType supportedType;
    private SpotStatus status;
    private Vehicle parkedVehicle;

    public ParkingSpot(int spotNumber, VehicleType supportedType) {
        this.spotNumber = spotNumber;
        this.supportedType = supportedType;
        this.status = SpotStatus.AVAILABLE;
    }

    public boolean isAvailable() {
        return status == SpotStatus.AVAILABLE;
    }

    public boolean canFit(VehicleType type) {
        return supportedType == type && isAvailable();
    }

    public void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.status = SpotStatus.OCCUPIED;
    }

    public void freeSpot() {
        this.parkedVehicle = null;
        this.status = SpotStatus.AVAILABLE;
    }

    public int getSpotNumber() { return spotNumber; }
    public VehicleType getSupportedType() { return supportedType; }
    public Vehicle getParkedVehicle() { return parkedVehicle; }
}
