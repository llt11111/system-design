package com.example.systemdesign.moviebooking.strategy;

import com.example.systemdesign.moviebooking.SeatType;
import com.example.systemdesign.moviebooking.ShowTime;

public interface PricingStrategy {
    double calculatePrice(SeatType seatType, ShowTime showTime);
}
