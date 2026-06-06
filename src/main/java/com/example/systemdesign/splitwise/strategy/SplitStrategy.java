package com.example.systemdesign.splitwise.strategy;

import java.util.List;
import java.util.Map;

public interface SplitStrategy {
    Map<String, Double> split(double amount, String paidBy, List<String> participants, Map<String, Double> params);
}
