package com.example.systemdesign.moviebooking.strategy;

public class UpiPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing UPI payment of Rs." + amount);
        return true;
    }

    @Override
    public String getMethodName() { return "UPI"; }
}
