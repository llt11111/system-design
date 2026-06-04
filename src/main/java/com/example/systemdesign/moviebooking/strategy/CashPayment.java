package com.example.systemdesign.moviebooking.strategy;

public class CashPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing CASH payment of Rs." + amount);
        return true;
    }

    @Override
    public String getMethodName() { return "CASH"; }
}
