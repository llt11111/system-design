package com.example.systemdesign.parkinglot.dto;

import com.example.systemdesign.parkinglot.VehicleType;
import java.time.LocalDateTime;

public class Ticket {
    private final String ticketId;
    private final String vehicleNumber;
    private final VehicleType vehicleType;
    private final int floorNumber;
    private final int spotNumber;
    private final LocalDateTime entryTime;

    public Ticket(String ticketId, String vehicleNumber, VehicleType vehicleType,
                  int floorNumber, int spotNumber, LocalDateTime entryTime) {
        this.ticketId = ticketId;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.floorNumber = floorNumber;
        this.spotNumber = spotNumber;
        this.entryTime = entryTime;
    }

    public String getTicketId() { return ticketId; }
    public String getVehicleNumber() { return vehicleNumber; }
    public VehicleType getVehicleType() { return vehicleType; }
    public int getFloorNumber() { return floorNumber; }
    public int getSpotNumber() { return spotNumber; }
    public LocalDateTime getEntryTime() { return entryTime; }

    @Override
    public String toString() {
        return "Ticket{id='" + ticketId + "', vehicle='" + vehicleNumber +
               "', type=" + vehicleType + ", floor=" + floorNumber +
               ", spot=" + spotNumber + ", entry=" + entryTime + "}";
    }
}
