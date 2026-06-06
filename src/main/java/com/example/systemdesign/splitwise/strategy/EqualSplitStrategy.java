package com.example.systemdesign.splitwise.strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EqualSplitStrategy implements SplitStrategy {
    @Override
    public Map<String, Double> split(double amount, String paidBy, List<String> participants, Map<String, Double> params) {
        Map<String, Double> splits = new HashMap<>();
        double perPerson = amount / participants.size();
        for (String userId : participants) {
            if (!userId.equals(paidBy)) {
                splits.put(userId, perPerson);
            }
        }
        return splits;
    }
}
