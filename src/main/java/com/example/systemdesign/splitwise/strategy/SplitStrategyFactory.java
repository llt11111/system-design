package com.example.systemdesign.splitwise.strategy;

import com.example.systemdesign.splitwise.SplitType;

public class SplitStrategyFactory {
    public static SplitStrategy getStrategy(SplitType type) {
        switch (type) {
            case EQUAL:      return new EqualSplitStrategy();
            case EXACT:      return new ExactSplitStrategy();
            case PERCENTAGE: return new PercentageSplitStrategy();
            default: throw new IllegalArgumentException("Unknown split type: " + type);
        }
    }
}
