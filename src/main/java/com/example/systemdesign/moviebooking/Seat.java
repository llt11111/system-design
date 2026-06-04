package com.example.systemdesign.moviebooking;

public class Seat {
    private final String seatId;
    private final int row;
    private final int col;
    private final SeatType type;
    private boolean booked;

    public Seat(String seatId, int row, int col, SeatType type) {
        this.seatId = seatId;
        this.row = row;
        this.col = col;
        this.type = type;
        this.booked = false;
    }

    public String getSeatId() { return seatId; }
    public int getRow() { return row; }
    public int getCol() { return col; }
    public SeatType getType() { return type; }
    public boolean isBooked() { return booked; }
    public void setBooked(boolean booked) { this.booked = booked; }

    @Override
    public String toString() {
        return seatId + "(" + type + ")" + (booked ? "[X]" : "[O]");
    }
}
