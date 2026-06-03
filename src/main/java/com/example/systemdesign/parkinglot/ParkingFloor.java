package com.example.systemdesign.parkinglot;

import com.example.systemdesign.parkinglot.vehicle.Vehicle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingFloor {
    private final int floorNumber;
    private final List<ParkingSpot> spots;

    public ParkingFloor(int floorNumber, int bikeSpots, int carSpots, int truckSpots) {
        this.floorNumber = floorNumber;
        this.spots = new ArrayList<>();
        int spotNum = 1;
        for (int i = 0; i < bikeSpots; i++) spots.add(new ParkingSpot(spotNum++, VehicleType.BIKE));
        for (int i = 0; i < carSpots; i++) spots.add(new ParkingSpot(spotNum++, VehicleType.CAR));
        for (int i = 0; i < truckSpots; i++) spots.add(new ParkingSpot(spotNum++, VehicleType.TRUCK));
    }

    public ParkingSpot findAvailableSpot(VehicleType type) {
        List<ParkingSpot> available = spots.stream()
                .filter(s -> s.canFit(type))
                .collect(Collectors.toList());
        if (available.isEmpty()) return null;
        Collections.shuffle(available);
        return available.get(0);
    }

    public ParkingSpot findSpotByVehicle(String vehicleNumber) {
        return spots.stream()
                .filter(s -> !s.isAvailable() && s.getParkedVehicle() != null
                        && s.getParkedVehicle().getVehicleNumber().equals(vehicleNumber))
                .findFirst().orElse(null);
    }

    public int getFloorNumber() { return floorNumber; }
}
