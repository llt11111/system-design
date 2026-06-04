package com.example.systemdesign.moviebooking.factory;

import com.example.systemdesign.moviebooking.Seat;
import com.example.systemdesign.moviebooking.SeatType;

public class SeatFactory {
    public static Seat createSeat(int row, int col, SeatType type) {
        String seatId = (char) ('A' + row - 1) + String.valueOf(col);
        return new Seat(seatId, row, col, type);
    }
}
