package com.example.systemdesign.moviebooking.strategy;

import com.example.systemdesign.moviebooking.SeatType;
import com.example.systemdesign.moviebooking.ShowTime;

public class DefaultPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(SeatType seatType, ShowTime showTime) {
        double basePrice = getBasePrice(seatType);
        double multiplier = getTimeMultiplier(showTime);
        return basePrice * multiplier;
    }

    private double getBasePrice(SeatType seatType) {
        switch (seatType) {
            case REGULAR: return 150.0;
            case PREMIUM: return 300.0;
            default: return 150.0;
        }
    }

    private double getTimeMultiplier(ShowTime showTime) {
        switch (showTime) {
            case MORNING:   return 0.8;
            case AFTERNOON: return 1.0;
            case EVENING:   return 1.2;
            case NIGHT:     return 1.5;
            default: return 1.0;
        }
    }
}
