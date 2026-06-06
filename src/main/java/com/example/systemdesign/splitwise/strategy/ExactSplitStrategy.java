package com.example.systemdesign.splitwise.strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExactSplitStrategy implements SplitStrategy {
    @Override
    public Map<String, Double> split(double amount, String paidBy, List<String> participants, Map<String, Double> params) {
        Map<String, Double> splits = new HashMap<>();
        for (Map.Entry<String, Double> entry : params.entrySet()) {
            if (!entry.getKey().equals(paidBy)) {
                splits.put(entry.getKey(), entry.getValue());
            }
        }
        return splits;
    }
}
