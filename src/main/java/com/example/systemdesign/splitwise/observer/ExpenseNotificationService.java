package com.example.systemdesign.splitwise.observer;

public class ExpenseNotificationService implements ExpenseObserver {
    @Override
    public void onExpenseAdded(String expenseId, String paidBy, double amount, String description) {
        System.out.println("[NOTIFY] " + paidBy + " added expense '" + description + "' Rs." + amount);
    }

    @Override
    public void onSettlement(String fromUser, String toUser, double amount) {
        System.out.println("[NOTIFY] " + fromUser + " settled Rs." + String.format("%.1f", amount) + " with " + toUser);
    }
}
