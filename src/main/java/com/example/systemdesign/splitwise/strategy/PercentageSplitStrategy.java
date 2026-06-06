package com.example.systemdesign.splitwise.strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PercentageSplitStrategy implements SplitStrategy {
    @Override
    public Map<String, Double> split(double amount, String paidBy, List<String> participants, Map<String, Double> params) {
        Map<String, Double> splits = new HashMap<>();
        for (Map.Entry<String, Double> entry : params.entrySet()) {
            if (!entry.getKey().equals(paidBy)) {
                splits.put(entry.getKey(), amount * (entry.getValue() / 100));
            }
        }
        return splits;
    }
}
