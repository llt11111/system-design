# Splitwise (Expense Sharing) System Design

## Package Structure

```
splitwise/
├── SplitType.java                (enum: EQUAL, EXACT, PERCENTAGE)
├── User.java                     (userId, name)
├── Group.java                    (members, add/remove)
├── ExpenseService.java           (Singleton - core orchestrator)
├── Main.java                     (demo)
├── dto/
│   ├── Expense.java              (paidBy, amount, description, splits)
│   └── Balance.java              (fromUser owes toUser amount)
├── factory/
│   └── ExpenseFactory.java       (creates Expense with auto-generated ID)
├── strategy/
│   ├── SplitStrategy.java        (interface: calculate splits)
│   ├── EqualSplitStrategy.java   (amount / N participants)
│   ├── ExactSplitStrategy.java   (user-specified exact amounts)
│   ├── PercentageSplitStrategy.java (user-specified percentages)
│   └── SplitStrategyFactory.java
└── observer/
    ├── ExpenseObserver.java      (Observer interface)
    └── ExpenseNotificationService.java (notifies on expense/settlement)
```

## Design Patterns Used

| Pattern | Class | Purpose |
|---------|-------|---------|
| Singleton | ExpenseService | Single service manages users, groups, expenses, balances |
| Factory | ExpenseFactory | Creates Expense with auto-generated ID |
| Strategy | SplitStrategy | Different split calculations (equal/exact/percentage) |
| Observer | ExpenseObserver | Notifies on expense added and settlement |

## Split Types

| Type | Calculation | Example (Rs.1000, 4 people) |
|------|-------------|----------------------------|
| EQUAL | amount / N | Each owes Rs.250 |
| EXACT | User-specified amounts | A=400, B=300, C=300 |
| PERCENTAGE | amount × (percent/100) | A=40%, B=30%, C=30% |

## System Flow

```
Add Expense:
  User → ExpenseService.addExpense(paidBy, amount, description, splitType, participants, params)
    → SplitStrategyFactory.getStrategy(splitType)
    → Strategy calculates who owes how much
    → Balance sheet updated (debt simplification)
    → ExpenseFactory creates Expense record
    → ExpenseObserver notified

Settle:
  User → ExpenseService.settle(fromUser, toUser, amount)
    → Balance reduced between users
    → ExpenseObserver notified

Get Balances:
  User → ExpenseService.getBalances(userId)
    → Returns list of Balance (who owes whom how much)
    → Includes both: what user owes + what others owe user
```

## Balance Simplification

When updating balances, the system automatically simplifies:
- If A owes B Rs.500 and B owes A Rs.300 → simplified to A owes B Rs.200
- Prevents circular debts between two users
