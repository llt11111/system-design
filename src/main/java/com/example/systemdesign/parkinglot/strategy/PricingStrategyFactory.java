package com.example.systemdesign.parkinglot.strategy;

import com.example.systemdesign.parkinglot.VehicleType;

public class PricingStrategyFactory {
    public static PricingStrategy getStrategy(VehicleType type) {
        switch (type) {
            case BIKE:  return new BikePricingStrategy();
            case CAR:   return new CarPricingStrategy();
            case TRUCK: return new TruckPricingStrategy();
            default: throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }
    }
}
