package com.example.systemdesign.splitwise;

import com.example.systemdesign.splitwise.dto.Balance;
import com.example.systemdesign.splitwise.dto.Expense;
import com.example.systemdesign.splitwise.factory.ExpenseFactory;
import com.example.systemdesign.splitwise.observer.ExpenseObserver;
import com.example.systemdesign.splitwise.strategy.SplitStrategy;
import com.example.systemdesign.splitwise.strategy.SplitStrategyFactory;

import java.util.*;

public class ExpenseService {
    private static ExpenseService instance;

    private final Map<String, User> users;
    private final Map<String, Group> groups;
    private final List<Expense> expenses;
    private final Map<String, Map<String, Double>> balanceSheet; // from → (to → amount)
    private final List<ExpenseObserver> observers;
    private int groupCounter;

    private ExpenseService() {
        this.users = new HashMap<>();
        this.groups = new HashMap<>();
        this.expenses = new ArrayList<>();
        this.balanceSheet = new HashMap<>();
        this.observers = new ArrayList<>();
        this.groupCounter = 0;
    }

    public static ExpenseService getInstance() {
        if (instance == null) instance = new ExpenseService();
        return instance;
    }

    public void addObserver(ExpenseObserver observer) { observers.add(observer); }
    public void registerUser(User user) { users.put(user.getUserId(), user); }

    public Group createGroup(String name, List<String> memberIds) {
        String groupId = "GRP-" + (++groupCounter);
        Group group = new Group(groupId, name, memberIds);
        groups.put(groupId, group);
        return group;
    }

    public Expense addExpense(String paidBy, double amount, String description,
                              SplitType splitType, List<String> participants, Map<String, Double> params) {
        SplitStrategy strategy = SplitStrategyFactory.getStrategy(splitType);
        Map<String, Double> splits = strategy.split(amount, paidBy, participants, params);

        Expense expense = ExpenseFactory.createExpense(paidBy, amount, description, splitType, splits);
        expenses.add(expense);

        // Update balance sheet
        for (Map.Entry<String, Double> entry : splits.entrySet()) {
            updateBalance(entry.getKey(), paidBy, entry.getValue());
        }

        observers.forEach(o -> o.onExpenseAdded(expense.getExpenseId(), paidBy, amount, description));
        return expense;
    }

    public void settle(String fromUserId, String toUserId, double amount) {
        updateBalance(toUserId, fromUserId, amount); // reverse the debt
        observers.forEach(o -> o.onSettlement(fromUserId, toUserId, amount));
    }

    private void updateBalance(String debtor, String creditor, double amount) {
        // Check if creditor already owes debtor (simplify)
        double existingReverse = getDirectBalance(creditor, debtor);
        if (existingReverse > 0) {
            if (amount >= existingReverse) {
                setBalance(creditor, debtor, 0);
                setBalance(debtor, creditor, amount - existingReverse);
            } else {
                setBalance(creditor, debtor, existingReverse - amount);
            }
        } else {
            double existing = getDirectBalance(debtor, creditor);
            setBalance(debtor, creditor, existing + amount);
        }
    }

    private double getDirectBalance(String from, String to) {
        return balanceSheet.getOrDefault(from, new HashMap<>()).getOrDefault(to, 0.0);
    }

    private void setBalance(String from, String to, double amount) {
        if (amount <= 0) {
            balanceSheet.getOrDefault(from, new HashMap<>()).remove(to);
        } else {
            balanceSheet.computeIfAbsent(from, k -> new HashMap<>()).put(to, amount);
        }
    }

    public List<Balance> getBalances(String userId) {
        List<Balance> result = new ArrayList<>();
        // What user owes others
        Map<String, Double> owes = balanceSheet.getOrDefault(userId, new HashMap<>());
        owes.forEach((to, amt) -> { if (amt > 0) result.add(new Balance(userId, to, amt)); });
        // What others owe user
        for (Map.Entry<String, Map<String, Double>> entry : balanceSheet.entrySet()) {
            Double amt = entry.getValue().get(userId);
            if (amt != null && amt > 0) result.add(new Balance(entry.getKey(), userId, amt));
        }
        return result;
    }

    public static void reset() { instance = null; }
}
