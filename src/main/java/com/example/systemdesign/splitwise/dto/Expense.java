package com.example.systemdesign.splitwise.dto;

import com.example.systemdesign.splitwise.SplitType;
import java.util.Map;

public class Expense {
    private final String expenseId;
    private final String paidBy;
    private final double amount;
    private final String description;
    private final SplitType splitType;
    private final Map<String, Double> splits; // userId → amount owed

    public Expense(String expenseId, String paidBy, double amount, String description,
                   SplitType splitType, Map<String, Double> splits) {
        this.expenseId = expenseId;
        this.paidBy = paidBy;
        this.amount = amount;
        this.description = description;
        this.splitType = splitType;
        this.splits = splits;
    }

    public String getExpenseId() { return expenseId; }
    public String getPaidBy() { return paidBy; }
    public double getAmount() { return amount; }
    public String getDescription() { return description; }
    public SplitType getSplitType() { return splitType; }
    public Map<String, Double> getSplits() { return splits; }

    @Override
    public String toString() {
        return "Expense{'" + description + "', Rs." + amount + ", paidBy=" + paidBy + ", type=" + splitType + "}";
    }
}
