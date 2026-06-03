package com.example.systemdesign.parkinglot.dto;

import com.example.systemdesign.parkinglot.VehicleType;
import java.time.LocalDateTime;

public class Receipt {
    private final String ticketId;
    private final String vehicleNumber;
    private final VehicleType vehicleType;
    private final LocalDateTime entryTime;
    private final LocalDateTime exitTime;
    private final long hoursParked;
    private final double amount;

    public Receipt(String ticketId, String vehicleNumber, VehicleType vehicleType,
                   LocalDateTime entryTime, LocalDateTime exitTime, long hoursParked, double amount) {
        this.ticketId = ticketId;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.hoursParked = hoursParked;
        this.amount = amount;
    }

    public double getAmount() { return amount; }

    @Override
    public String toString() {
        return "Receipt{ticket='" + ticketId + "', vehicle='" + vehicleNumber +
               "', type=" + vehicleType + ", entry=" + entryTime +
               ", exit=" + exitTime + ", hours=" + hoursParked +
               ", amount=Rs." + amount + "}";
    }
}
