package com.example.systemdesign.splitwise.dto;

public class Balance {
    private final String fromUserId;
    private final String toUserId;
    private final double amount;

    public Balance(String fromUserId, String toUserId, double amount) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.amount = amount;
    }

    public String getFromUserId() { return fromUserId; }
    public String getToUserId() { return toUserId; }
    public double getAmount() { return amount; }

    @Override
    public String toString() {
        return fromUserId + " owes " + toUserId + " → Rs." + String.format("%.1f", amount);
    }
}
