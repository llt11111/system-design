package com.example.systemdesign.moviebooking;

import com.example.systemdesign.moviebooking.dto.Booking;
import com.example.systemdesign.moviebooking.dto.Refund;
import com.example.systemdesign.moviebooking.observer.NotificationService;

import java.time.LocalDate;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        BookingSystem system = BookingSystem.getInstance();
        system.addObserver(new NotificationService());

        // Setup theater with 2 screens
        Theater theater = new Theater("TH-1", "PVR Cinemas");
        Screen screen1 = new Screen("SCR-1", 5, 2, 10); // 5 regular rows, 2 premium rows
        Screen screen2 = new Screen("SCR-2", 4, 1, 8);
        theater.addScreen(screen1);
        theater.addScreen(screen2);

        // Create movies
        Movie movie1 = new Movie("M1", "Inception", 148, "Sci-Fi");
        Movie movie2 = new Movie("M2", "The Dark Knight", 152, "Action");

        // Create shows
        Show show1 = new Show("SH-1", movie1, screen1, ShowTime.EVENING, LocalDate.now());
        Show show2 = new Show("SH-2", movie2, screen2, ShowTime.MORNING, LocalDate.now());
        system.addShow(show1);
        system.addShow(show2);

        // Display available seats
        System.out.println("--- Available seats for " + movie1.getTitle() + " (Evening) ---");
        System.out.println("Premium: " + show1.getAvailableSeatsByType(SeatType.PREMIUM).size());
        System.out.println("Regular: " + show1.getAvailableSeatsByType(SeatType.REGULAR).size());

        // Book premium seats for Inception (Evening show)
        System.out.println("\n--- Booking Premium Seats ---");
        Booking b1 = system.bookTickets("SH-1", "Rahul", Arrays.asList("A1", "A2"), PaymentMethod.UPI);
        System.out.println(b1);

        // Book regular seats
        System.out.println("\n--- Booking Regular Seats ---");
        Booking b2 = system.bookTickets("SH-1", "Priya", Arrays.asList("C1", "C2", "C3"), PaymentMethod.CARD);
        System.out.println(b2);

        // Try booking already booked seat
        System.out.println("\n--- Try booking already booked seat ---");
        system.bookTickets("SH-1", "Amit", Arrays.asList("A1"), PaymentMethod.CASH);

        // Cancel booking
        System.out.println("\n--- Cancel Booking ---");
        Refund refund = system.cancelBooking(b1.getBookingId());
        System.out.println(refund);

        // Check availability after cancellation
        System.out.println("\n--- After Cancellation ---");
        System.out.println("Premium available: " + show1.getAvailableSeatsByType(SeatType.PREMIUM).size());
    }
}
