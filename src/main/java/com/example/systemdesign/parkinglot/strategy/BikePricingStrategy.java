package com.example.systemdesign.parkinglot.strategy;

public class BikePricingStrategy implements PricingStrategy {
    private static final double RATE_PER_HOUR = 10.0;

    @Override
    public double calculateCharge(long hoursParked) {
        return Math.max(1, hoursParked) * RATE_PER_HOUR;
    }
}
