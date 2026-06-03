package com.example.systemdesign.parkinglot;

import com.example.systemdesign.parkinglot.dto.Receipt;
import com.example.systemdesign.parkinglot.dto.Ticket;
import com.example.systemdesign.parkinglot.observer.DisplayBoard;
import com.example.systemdesign.parkinglot.observer.ParkingLotObserver;
import com.example.systemdesign.parkinglot.strategy.PricingStrategy;
import com.example.systemdesign.parkinglot.strategy.PricingStrategyFactory;
import com.example.systemdesign.parkinglot.vehicle.Vehicle;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class ParkingLot {
    private static ParkingLot instance;

    private final String name;
    private final List<ParkingFloor> floors;
    private final Map<String, Ticket> activeTickets;
    private final List<ParkingLotObserver> observers;
    private int ticketCounter;

    private ParkingLot(String name, int totalFloors, int bikeSpotsPerFloor,
                       int carSpotsPerFloor, int truckSpotsPerFloor) {
        this.name = name;
        this.floors = new ArrayList<>();
        this.activeTickets = new HashMap<>();
        this.observers = new ArrayList<>();
        this.ticketCounter = 0;

        for (int i = 1; i <= totalFloors; i++) {
            floors.add(new ParkingFloor(i, bikeSpotsPerFloor, carSpotsPerFloor, truckSpotsPerFloor));
        }
    }

    public static ParkingLot init(String name, int floors, int bikeSpots, int carSpots, int truckSpots) {
        if (instance == null) {
            instance = new ParkingLot(name, floors, bikeSpots, carSpots, truckSpots);
        }
        return instance;
    }

    public static ParkingLot getInstance() {
        if (instance == null) throw new RuntimeException("ParkingLot not initialized");
        return instance;
    }

    public void addObserver(ParkingLotObserver observer) {
        observers.add(observer);
    }

    private void notifySpotTaken(int floor, VehicleType type) {
        observers.forEach(o -> o.onSpotTaken(floor, type));
    }

    private void notifySpotFreed(int floor, VehicleType type) {
        observers.forEach(o -> o.onSpotFreed(floor, type));
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        for (ParkingFloor floor : floors) {
            ParkingSpot spot = floor.findAvailableSpot(vehicle.getType());
            if (spot != null) {
                spot.parkVehicle(vehicle);
                String ticketId = "T-" + (++ticketCounter);
                Ticket ticket = new Ticket(ticketId, vehicle.getVehicleNumber(),
                        vehicle.getType(), floor.getFloorNumber(),
                        spot.getSpotNumber(), LocalDateTime.now());
                activeTickets.put(vehicle.getVehicleNumber(), ticket);
                notifySpotTaken(floor.getFloorNumber(), vehicle.getType());
                return ticket;
            }
        }
        System.out.println("No spot available for " + vehicle.getType());
        return null;
    }

    public Receipt unparkVehicle(String vehicleNumber) {
        Ticket ticket = activeTickets.remove(vehicleNumber);
        if (ticket == null) {
            System.out.println("No active ticket for vehicle: " + vehicleNumber);
            return null;
        }

        ParkingFloor floor = floors.get(ticket.getFloorNumber() - 1);
        ParkingSpot spot = floor.findSpotByVehicle(vehicleNumber);
        if (spot != null) spot.freeSpot();

        LocalDateTime exitTime = LocalDateTime.now();
        long hours = Duration.between(ticket.getEntryTime(), exitTime).toHours();
        PricingStrategy strategy = PricingStrategyFactory.getStrategy(ticket.getVehicleType());
        double amount = strategy.calculateCharge(hours);

        notifySpotFreed(ticket.getFloorNumber(), ticket.getVehicleType());

        return new Receipt(ticket.getTicketId(), vehicleNumber, ticket.getVehicleType(),
                ticket.getEntryTime(), exitTime, hours, amount);
    }

    public String getName() { return name; }

    // Reset for testing
    public static void reset() { instance = null; }
}
