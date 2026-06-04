package com.example.systemdesign.moviebooking.strategy;

import java.time.LocalDateTime;

public interface CancellationStrategy {
    double calculateRefund(double originalAmount, LocalDateTime bookingTime, LocalDateTime cancellationTime);
}
