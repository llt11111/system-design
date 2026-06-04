package com.example.systemdesign.moviebooking.dto;

public class Refund {
    private final String bookingId;
    private final double originalAmount;
    private final double refundAmount;
    private final double deduction;
    private final String reason;

    public Refund(String bookingId, double originalAmount, double refundAmount, double deduction, String reason) {
        this.bookingId = bookingId;
        this.originalAmount = originalAmount;
        this.refundAmount = refundAmount;
        this.deduction = deduction;
        this.reason = reason;
    }

    public String getBookingId() { return bookingId; }
    public double getOriginalAmount() { return originalAmount; }
    public double getRefundAmount() { return refundAmount; }
    public double getDeduction() { return deduction; }

    @Override
    public String toString() {
        return "Refund{booking='" + bookingId + "', original=Rs." + originalAmount +
               ", refund=Rs." + refundAmount + ", deduction=Rs." + deduction +
               ", reason='" + reason + "'}";
    }
}
