package com.example.systemdesign.parkinglot.strategy;

public class TruckPricingStrategy implements PricingStrategy {
    private static final double RATE_PER_HOUR = 30.0;

    @Override
    public double calculateCharge(long hoursParked) {
        return Math.max(1, hoursParked) * RATE_PER_HOUR;
    }
}
