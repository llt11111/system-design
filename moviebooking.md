# Movie Ticket Booking System Design

## Package Structure

```
moviebooking/
├── SeatType.java                 (enum: REGULAR, PREMIUM)
├── ShowTime.java                 (enum: MORNING, AFTERNOON, EVENING, NIGHT)
├── BookingStatus.java            (enum: CONFIRMED, CANCELLED)
├── PaymentMethod.java            (enum: CASH, CARD, UPI)
├── Movie.java                    (title, duration, genre)
├── Seat.java                     (seatId, row, col, type, booked status)
├── Screen.java                   (regular + premium rows, seats per row)
├── Show.java                     (movie + screen + showTime + date + seats)
├── Theater.java                  (multiple screens)
├── BookingSystem.java            (Singleton - core orchestrator)
├── Main.java                     (demo)
├── dto/
│   ├── Booking.java              (bookingId, seats, amount, payment, status)
│   └── Refund.java               (original amount, refund amount, deduction)
├── factory/
│   └── SeatFactory.java          (creates seats with auto-generated IDs)
├── strategy/
│   ├── PricingStrategy.java      (interface: price by seat type + show time)
│   ├── DefaultPricingStrategy.java (Regular Rs.150, Premium Rs.300 × time multiplier)
│   ├── PaymentStrategy.java      (interface: process payment)
│   ├── CashPayment.java
│   ├── CardPayment.java
│   ├── UpiPayment.java
│   ├── PaymentStrategyFactory.java
│   ├── CancellationStrategy.java (interface: calculate refund)
│   └── DefaultCancellationStrategy.java (time-based refund policy)
└── observer/
    ├── BookingObserver.java       (Observer interface)
    └── NotificationService.java   (notifies on booking/cancellation)
```

## Design Patterns Used

| Pattern | Class | Purpose |
|---------|-------|---------|
| Singleton | BookingSystem | Single system manages all shows, bookings, and cancellations |
| Factory | SeatFactory | Creates Seat objects with auto-generated IDs (A1, B2, etc.) |
| Strategy | PricingStrategy | Calculates ticket price based on seat type and show time |
| Strategy | PaymentStrategy | Processes payment via Cash, Card, or UPI |
| Strategy | CancellationStrategy | Calculates refund based on cancellation timing |
| Observer | BookingObserver | Notifications on booking confirmation and cancellation |

## Key OOP Concepts

- **Abstraction** - PricingStrategy, PaymentStrategy, CancellationStrategy, BookingObserver are interfaces
- **Encapsulation** - Seat booking state managed internally; pricing logic hidden in strategies
- **Inheritance** - CashPayment, CardPayment, UpiPayment implement PaymentStrategy
- **Polymorphism** - Pricing and cancellation strategies are interchangeable at runtime

## Pricing Model

| Seat Type | Base Price | Morning (0.8x) | Afternoon (1.0x) | Evening (1.2x) | Night (1.5x) |
|-----------|-----------|-----------------|-------------------|-----------------|---------------|
| Regular | Rs.150 | Rs.120 | Rs.150 | Rs.180 | Rs.225 |
| Premium | Rs.300 | Rs.240 | Rs.300 | Rs.360 | Rs.450 |

## Cancellation Policy

| Time Before Show | Refund |
|------------------|--------|
| > 24 hours | 100% |
| 6 - 24 hours | 75% |
| 2 - 6 hours | 50% |
| < 2 hours | 0% |

## System Flow

```
Book Tickets:
  Customer → BookingSystem.bookTickets(showId, name, seatIds, paymentMethod)
    → Validates show exists
    → Validates all selected seats are available
    → PricingStrategy calculates total (seatType × showTime multiplier)
    → PaymentStrategyFactory processes payment
    → Seats marked as booked
    → Booking DTO created (CONFIRMED)
    → BookingObserver notified

Cancel Booking:
  Customer → BookingSystem.cancelBooking(bookingId)
    → CancellationStrategy calculates refund based on timing
    → Seats freed (marked available again)
    → Booking status → CANCELLED
    → Refund DTO created with deduction details
    → BookingObserver notified
```

## Class Responsibilities

- **BookingSystem** - Singleton. Manages shows, bookings. Handles book/cancel with strategy delegation
- **Theater** - Holds multiple screens
- **Screen** - Defines seat layout (regular + premium rows). Generates seats via SeatFactory
- **Show** - Links movie + screen + time. Manages seat availability for that specific show
- **Seat** - Individual seat with ID, type, and booking status
- **Movie** - Movie metadata (title, duration, genre)
- **Booking** - DTO with booking details, amount, payment method, and status
- **Refund** - DTO with original amount, refund amount, and deduction breakdown
- **SeatFactory** - Generates seats with IDs like A1, B3 based on row/col
- **DefaultPricingStrategy** - Base price × time multiplier
- **DefaultCancellationStrategy** - Time-based refund percentage
- **NotificationService** - Observer logging booking/cancellation events
