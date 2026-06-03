package com.example.systemdesign.parkinglot.observer;

import com.example.systemdesign.parkinglot.VehicleType;
import java.util.HashMap;
import java.util.Map;

public class DisplayBoard implements ParkingLotObserver {
    private final Map<Integer, Map<VehicleType, Integer>> availableSpots;

    public DisplayBoard(int totalFloors, Map<VehicleType, Integer> spotsPerType) {
        availableSpots = new HashMap<>();
        for (int i = 1; i <= totalFloors; i++) {
            availableSpots.put(i, new HashMap<>(spotsPerType));
        }
    }

    @Override
    public void onSpotTaken(int floor, VehicleType type) {
        availableSpots.get(floor).merge(type, -1, Integer::sum);
    }

    @Override
    public void onSpotFreed(int floor, VehicleType type) {
        availableSpots.get(floor).merge(type, 1, Integer::sum);
    }

    public void display() {
        System.out.println("========= DISPLAY BOARD =========");
        for (Map.Entry<Integer, Map<VehicleType, Integer>> floorEntry : availableSpots.entrySet()) {
            System.out.println("Floor " + floorEntry.getKey() + ":");
            for (Map.Entry<VehicleType, Integer> spotEntry : floorEntry.getValue().entrySet()) {
                System.out.println("  " + spotEntry.getKey() + " spots available: " + spotEntry.getValue());
            }
        }
        System.out.println("=================================");
    }
}
