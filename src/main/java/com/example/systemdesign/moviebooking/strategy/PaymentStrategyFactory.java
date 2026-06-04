package com.example.systemdesign.moviebooking.strategy;

import com.example.systemdesign.moviebooking.PaymentMethod;

public class PaymentStrategyFactory {
    public static PaymentStrategy getStrategy(PaymentMethod method) {
        switch (method) {
            case CASH: return new CashPayment();
            case CARD: return new CardPayment();
            case UPI:  return new UpiPayment();
            default: throw new IllegalArgumentException("Unknown payment method: " + method);
        }
    }
}
