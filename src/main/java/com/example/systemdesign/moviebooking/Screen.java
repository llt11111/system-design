package com.example.systemdesign.moviebooking;

import com.example.systemdesign.moviebooking.factory.SeatFactory;
import java.util.ArrayList;
import java.util.List;

public class Screen {
    private final String screenId;
    private final int regularRows;
    private final int premiumRows;
    private final int seatsPerRow;

    public Screen(String screenId, int regularRows, int premiumRows, int seatsPerRow) {
        this.screenId = screenId;
        this.regularRows = regularRows;
        this.premiumRows = premiumRows;
        this.seatsPerRow = seatsPerRow;
    }

    public List<Seat> generateSeats() {
        List<Seat> seats = new ArrayList<>();
        int row = 1;
        for (int i = 0; i < premiumRows; i++, row++) {
            for (int col = 1; col <= seatsPerRow; col++) {
                seats.add(SeatFactory.createSeat(row, col, SeatType.PREMIUM));
            }
        }
        for (int i = 0; i < regularRows; i++, row++) {
            for (int col = 1; col <= seatsPerRow; col++) {
                seats.add(SeatFactory.createSeat(row, col, SeatType.REGULAR));
            }
        }
        return seats;
    }

    public String getScreenId() { return screenId; }
    public int getTotalSeats() { return (regularRows + premiumRows) * seatsPerRow; }
}
