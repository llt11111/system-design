package com.example.systemdesign.parkinglot;

import com.example.systemdesign.parkinglot.dto.Receipt;
import com.example.systemdesign.parkinglot.dto.Ticket;
import com.example.systemdesign.parkinglot.vehicle.Vehicle;

public class Gate {
    private final ParkingLot parkingLot;

    public Gate() {
        this.parkingLot = ParkingLot.getInstance();
    }

    public Ticket entry(Vehicle vehicle) {
        System.out.println("Vehicle entering: " + vehicle.getVehicleNumber() + " [" + vehicle.getType() + "]");
        Ticket ticket = parkingLot.parkVehicle(vehicle);
        if (ticket != null) {
            System.out.println("Ticket issued: " + ticket);
        }
        return ticket;
    }

    public Receipt exit(String vehicleNumber) {
        System.out.println("Vehicle exiting: " + vehicleNumber);
        Receipt receipt = parkingLot.unparkVehicle(vehicleNumber);
        if (receipt != null) {
            System.out.println("Receipt: " + receipt);
        }
        return receipt;
    }
}
