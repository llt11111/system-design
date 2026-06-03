package com.example.systemdesign.parkinglot.strategy;

public class CarPricingStrategy implements PricingStrategy {
    private static final double RATE_PER_HOUR = 20.0;

    @Override
    public double calculateCharge(long hoursParked) {
        return Math.max(1, hoursParked) * RATE_PER_HOUR;
    }
}
