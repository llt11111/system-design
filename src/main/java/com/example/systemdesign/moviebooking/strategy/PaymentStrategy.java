package com.example.systemdesign.moviebooking.strategy;

public interface PaymentStrategy {
    boolean processPayment(double amount);
    String getMethodName();
}
