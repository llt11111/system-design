package com.example.systemdesign.moviebooking.dto;

import com.example.systemdesign.moviebooking.BookingStatus;
import com.example.systemdesign.moviebooking.PaymentMethod;

import java.time.LocalDateTime;
import java.util.List;

public class Booking {
    private final String bookingId;
    private final String showId;
    private final String customerName;
    private final List<String> seatIds;
    private final double totalAmount;
    private final PaymentMethod paymentMethod;
    private final LocalDateTime bookingTime;
    private BookingStatus status;

    public Booking(String bookingId, String showId, String customerName,
                   List<String> seatIds, double totalAmount,
                   PaymentMethod paymentMethod, LocalDateTime bookingTime) {
        this.bookingId = bookingId;
        this.showId = showId;
        this.customerName = customerName;
        this.seatIds = seatIds;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.bookingTime = bookingTime;
        this.status = BookingStatus.CONFIRMED;
    }

    public String getBookingId() { return bookingId; }
    public String getShowId() { return showId; }
    public String getCustomerName() { return customerName; }
    public List<String> getSeatIds() { return seatIds; }
    public double getTotalAmount() { return totalAmount; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public LocalDateTime getBookingTime() { return bookingTime; }
    public BookingStatus getStatus() { return status; }
    public void setStatus(BookingStatus status) { this.status = status; }

    @Override
    public String toString() {
        return "Booking{id='" + bookingId + "', show='" + showId +
               "', customer='" + customerName + "', seats=" + seatIds +
               ", amount=Rs." + totalAmount + ", payment=" + paymentMethod +
               ", status=" + status + "}";
    }
}
