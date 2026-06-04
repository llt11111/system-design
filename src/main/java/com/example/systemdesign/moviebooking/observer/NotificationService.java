package com.example.systemdesign.moviebooking.observer;

import com.example.systemdesign.moviebooking.dto.Booking;
import com.example.systemdesign.moviebooking.dto.Refund;

public class NotificationService implements BookingObserver {
    @Override
    public void onBookingConfirmed(Booking booking) {
        System.out.println("[NOTIFICATION] Booking confirmed: " + booking.getBookingId() +
                " | Seats: " + booking.getSeatIds() + " | Amount: Rs." + booking.getTotalAmount());
    }

    @Override
    public void onBookingCancelled(Booking booking, Refund refund) {
        System.out.println("[NOTIFICATION] Booking cancelled: " + booking.getBookingId() +
                " | Refund: Rs." + refund.getRefundAmount());
    }
}
