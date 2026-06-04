package com.example.systemdesign.moviebooking.strategy;

public class CardPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing CARD payment of Rs." + amount);
        return true;
    }

    @Override
    public String getMethodName() { return "CARD"; }
}
