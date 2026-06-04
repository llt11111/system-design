package com.example.systemdesign.moviebooking.observer;

import com.example.systemdesign.moviebooking.dto.Booking;
import com.example.systemdesign.moviebooking.dto.Refund;

public interface BookingObserver {
    void onBookingConfirmed(Booking booking);
    void onBookingCancelled(Booking booking, Refund refund);
}
