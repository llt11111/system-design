package com.example.systemdesign.moviebooking;

import com.example.systemdesign.moviebooking.dto.Booking;
import com.example.systemdesign.moviebooking.dto.Refund;
import com.example.systemdesign.moviebooking.observer.BookingObserver;
import com.example.systemdesign.moviebooking.strategy.*;

import java.time.LocalDateTime;
import java.util.*;

public class BookingSystem {
    private static BookingSystem instance;

    private final Map<String, Show> shows;
    private final Map<String, Booking> bookings;
    private final List<BookingObserver> observers;
    private final PricingStrategy pricingStrategy;
    private final CancellationStrategy cancellationStrategy;
    private int bookingCounter;

    private BookingSystem() {
        this.shows = new HashMap<>();
        this.bookings = new HashMap<>();
        this.observers = new ArrayList<>();
        this.pricingStrategy = new DefaultPricingStrategy();
        this.cancellationStrategy = new DefaultCancellationStrategy();
        this.bookingCounter = 0;
    }

    public static BookingSystem getInstance() {
        if (instance == null) {
            instance = new BookingSystem();
        }
        return instance;
    }

    public void addObserver(BookingObserver observer) { observers.add(observer); }
    public void addShow(Show show) { shows.put(show.getShowId(), show); }

    public Booking bookTickets(String showId, String customerName,
                               List<String> seatIds, PaymentMethod paymentMethod) {
        Show show = shows.get(showId);
        if (show == null) { System.out.println("Show not found: " + showId); return null; }

        // Validate seats
        double totalAmount = 0;
        List<Seat> seatsToBook = new ArrayList<>();
        for (String seatId : seatIds) {
            Seat seat = show.getSeatById(seatId);
            if (seat == null || seat.isBooked()) {
                System.out.println("Seat unavailable: " + seatId);
                return null;
            }
            seatsToBook.add(seat);
            totalAmount += pricingStrategy.calculatePrice(seat.getType(), show.getShowTime());
        }

        // Process payment
        PaymentStrategy payment = PaymentStrategyFactory.getStrategy(paymentMethod);
        if (!payment.processPayment(totalAmount)) {
            System.out.println("Payment failed");
            return null;
        }

        // Book seats
        seatsToBook.forEach(s -> s.setBooked(true));

        String bookingId = "BK-" + (++bookingCounter);
        Booking booking = new Booking(bookingId, showId, customerName, seatIds,
                totalAmount, paymentMethod, LocalDateTime.now());
        bookings.put(bookingId, booking);

        observers.forEach(o -> o.onBookingConfirmed(booking));
        return booking;
    }

    public Refund cancelBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking == null || booking.getStatus() == BookingStatus.CANCELLED) {
            System.out.println("Invalid booking or already cancelled: " + bookingId);
            return null;
        }

        // Calculate refund
        double refundAmount = cancellationStrategy.calculateRefund(
                booking.getTotalAmount(), booking.getBookingTime(), LocalDateTime.now());
        double deduction = booking.getTotalAmount() - refundAmount;

        // Free seats
        Show show = shows.get(booking.getShowId());
        for (String seatId : booking.getSeatIds()) {
            Seat seat = show.getSeatById(seatId);
            if (seat != null) seat.setBooked(false);
        }

        booking.setStatus(BookingStatus.CANCELLED);
        String reason = refundAmount == 0 ? "Cancelled too late (< 2hrs)" : "Cancellation processed";
        Refund refund = new Refund(bookingId, booking.getTotalAmount(), refundAmount, deduction, reason);

        observers.forEach(o -> o.onBookingCancelled(booking, refund));
        return refund;
    }

    public Show getShow(String showId) { return shows.get(showId); }
    public static void reset() { instance = null; }
}
