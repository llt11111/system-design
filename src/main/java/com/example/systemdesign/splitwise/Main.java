package com.example.systemdesign.splitwise;

import com.example.systemdesign.splitwise.dto.Balance;
import com.example.systemdesign.splitwise.observer.ExpenseNotificationService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ExpenseService service = ExpenseService.getInstance();
        service.addObserver(new ExpenseNotificationService());

        // Register users
        service.registerUser(new User("U1", "Rahul"));
        service.registerUser(new User("U2", "Priya"));
        service.registerUser(new User("U3", "Amit"));
        service.registerUser(new User("U4", "Neha"));

        // Create group
        Group trip = service.createGroup("Goa Trip", Arrays.asList("U1", "U2", "U3", "U4"));

        // Equal split: Rahul pays Rs.1000 dinner for 4
        System.out.println("--- Equal Split ---");
        service.addExpense("U1", 1000, "Dinner", SplitType.EQUAL,
                Arrays.asList("U1", "U2", "U3", "U4"), null);

        // Exact split: Priya pays Rs.500 cab
        System.out.println("\n--- Exact Split ---");
        Map<String, Double> exactParams = new HashMap<>();
        exactParams.put("U1", 200.0);
        exactParams.put("U3", 150.0);
        exactParams.put("U4", 150.0);
        service.addExpense("U2", 500, "Cab", SplitType.EXACT,
                Arrays.asList("U1", "U2", "U3", "U4"), exactParams);

        // Percentage split: Amit pays Rs.2000 hotel
        System.out.println("\n--- Percentage Split ---");
        Map<String, Double> pctParams = new HashMap<>();
        pctParams.put("U1", 30.0);
        pctParams.put("U2", 30.0);
        pctParams.put("U3", 20.0);
        pctParams.put("U4", 20.0);
        service.addExpense("U3", 2000, "Hotel", SplitType.PERCENTAGE,
                Arrays.asList("U1", "U2", "U3", "U4"), pctParams);

        // Check balances
        System.out.println("\n--- Rahul's Balances ---");
        service.getBalances("U1").forEach(System.out::println);

        System.out.println("\n--- Priya's Balances ---");
        service.getBalances("U2").forEach(System.out::println);

        // Settle
        System.out.println("\n--- Settlement ---");
        service.settle("U2", "U1", 250);

        System.out.println("\n--- After Settlement: Rahul's Balances ---");
        service.getBalances("U1").forEach(System.out::println);
    }
}
