package com.example.systemdesign.moviebooking.strategy;

import java.time.Duration;
import java.time.LocalDateTime;

public class DefaultCancellationStrategy implements CancellationStrategy {
    @Override
    public double calculateRefund(double originalAmount, LocalDateTime bookingTime, LocalDateTime cancellationTime) {
        long hoursBeforeShow = Duration.between(cancellationTime, bookingTime).toHours();

        if (hoursBeforeShow >= 24) {
            return originalAmount;           // Full refund if > 24 hrs
        } else if (hoursBeforeShow >= 6) {
            return originalAmount * 0.75;    // 75% refund if 6-24 hrs
        } else if (hoursBeforeShow >= 2) {
            return originalAmount * 0.50;    // 50% refund if 2-6 hrs
        }
        return 0;                            // No refund if < 2 hrs
    }
}
