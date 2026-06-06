package com.example.systemdesign.splitwise.observer;

public interface ExpenseObserver {
    void onExpenseAdded(String expenseId, String paidBy, double amount, String description);
    void onSettlement(String fromUser, String toUser, double amount);
}
