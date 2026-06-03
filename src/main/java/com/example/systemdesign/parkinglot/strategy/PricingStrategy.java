package com.example.systemdesign.parkinglot.strategy;

public interface PricingStrategy {
    double calculateCharge(long hoursParked);
}
