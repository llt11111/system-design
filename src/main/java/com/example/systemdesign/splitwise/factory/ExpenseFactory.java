package com.example.systemdesign.splitwise.factory;

import com.example.systemdesign.splitwise.SplitType;
import com.example.systemdesign.splitwise.dto.Expense;
import java.util.Map;

public class ExpenseFactory {
    private static int counter = 0;

    public static Expense createExpense(String paidBy, double amount, String description,
                                        SplitType splitType, Map<String, Double> splits) {
        return new Expense("EXP-" + (++counter), paidBy, amount, description, splitType, splits);
    }
}
