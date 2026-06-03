package com.example.systemdesign.parkinglot;

import com.example.systemdesign.parkinglot.factory.VehicleFactory;
import com.example.systemdesign.parkinglot.observer.DisplayBoard;
import com.example.systemdesign.parkinglot.vehicle.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Initialize parking lot: 3 floors, 5 bike/5 car/2 truck spots per floor
        ParkingLot lot = ParkingLot.init("City Mall Parking", 3, 5, 5, 2);

        // Setup display board (Observer)
        Map<VehicleType, Integer> spotsPerType = new HashMap<>();
        spotsPerType.put(VehicleType.BIKE, 5);
        spotsPerType.put(VehicleType.CAR, 5);
        spotsPerType.put(VehicleType.TRUCK, 2);
        DisplayBoard displayBoard = new DisplayBoard(3, spotsPerType);
        lot.addObserver(displayBoard);

        Gate gate = new Gate();

        // Show initial availability
        displayBoard.display();

        // Park vehicles using Factory pattern
        Vehicle bike1 = VehicleFactory.createVehicle(VehicleType.BIKE, "KA-01-1111");
        Vehicle car1 = VehicleFactory.createVehicle(VehicleType.CAR, "KA-01-2222");
        Vehicle car2 = VehicleFactory.createVehicle(VehicleType.CAR, "KA-01-3333");
        Vehicle truck1 = VehicleFactory.createVehicle(VehicleType.TRUCK, "KA-01-4444");

        System.out.println("\n--- PARKING VEHICLES ---");
        gate.entry(bike1);
        gate.entry(car1);
        gate.entry(car2);
        gate.entry(truck1);

        System.out.println("\n--- AFTER PARKING ---");
        displayBoard.display();

        // Unpark a car - generates receipt with charge
        System.out.println("\n--- UNPARKING VEHICLE ---");
        gate.exit("KA-01-2222");

        System.out.println("\n--- AFTER UNPARKING ---");
        displayBoard.display();
    }
}
