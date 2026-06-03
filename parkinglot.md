# Parking Lot System Design

## Package Structure

```
parkinglot/
├── VehicleType.java          (enum: BIKE, CAR, TRUCK)
├── SpotStatus.java           (enum: AVAILABLE, OCCUPIED)
├── ParkingSpot.java          (spot with type + status)
├── ParkingFloor.java         (floor with random spot allocation)
├── ParkingLot.java           (Singleton - core orchestrator)
├── Gate.java                 (single entry/exit gate)
├── Main.java                 (demo)
├── dto/
│   ├── Ticket.java           (issued on entry)
│   └── Receipt.java          (generated on exit with charge)
├── vehicle/
│   ├── Vehicle.java          (abstract)
│   ├── Bike.java
│   ├── Car.java
│   └── Truck.java
├── factory/
│   └── VehicleFactory.java   (Factory pattern)
├── strategy/
│   ├── PricingStrategy.java  (Strategy interface)
│   ├── BikePricingStrategy.java   (₹10/hr)
│   ├── CarPricingStrategy.java    (₹20/hr)
│   ├── TruckPricingStrategy.java  (₹30/hr)
│   └── PricingStrategyFactory.java
└── observer/
    ├── ParkingLotObserver.java (Observer interface)
    └── DisplayBoard.java       (shows available spots per floor per type)
```

## Design Patterns Used

| Pattern | Class | Purpose |
|---------|-------|---------|
| Singleton | ParkingLot | Ensures only one parking lot instance exists across the system |
| Factory | VehicleFactory | Creates Bike, Car, or Truck objects based on VehicleType |
| Strategy | PricingStrategy | Different hourly pricing per vehicle type (Bike ₹10, Car ₹20, Truck ₹30) |
| Observer | DisplayBoard | Auto-updates available spot count whenever a vehicle is parked or unparked |

## Key OOP Concepts

- **Abstraction** - Vehicle is an abstract class; PricingStrategy and ParkingLotObserver are interfaces
- **Encapsulation** - Private fields with getters across all classes
- **Inheritance** - Bike, Car, Truck extend Vehicle; pricing strategies implement PricingStrategy
- **Polymorphism** - PricingStrategy interface with different implementations per vehicle type

## System Flow

```
Entry:
  Vehicle → Gate.entry() → ParkingLot.parkVehicle()
    → ParkingFloor.findAvailableSpot() (random)
    → ParkingSpot.parkVehicle()
    → Ticket issued
    → DisplayBoard notified (Observer)

Exit:
  VehicleNumber → Gate.exit() → ParkingLot.unparkVehicle()
    → PricingStrategyFactory.getStrategy() (Strategy)
    → PricingStrategy.calculateCharge(hours)
    → ParkingSpot.freeSpot()
    → Receipt generated with amount
    → DisplayBoard notified (Observer)
```

## Class Responsibilities

- **ParkingLot** - Singleton. Manages floors, active tickets, observers. Handles park/unpark logic
- **ParkingFloor** - Holds list of ParkingSpots. Finds random available spot for a vehicle type
- **ParkingSpot** - Tracks spot number, supported vehicle type, status, and parked vehicle
- **Gate** - Single entry/exit point. Delegates to ParkingLot for park and unpark
- **Ticket** - DTO issued on entry with ticket ID, vehicle info, floor, spot, and entry time
- **Receipt** - DTO generated on exit with duration, calculated charge amount
- **DisplayBoard** - Observer that tracks and displays available spots per floor per vehicle type
- **VehicleFactory** - Creates vehicle instances based on VehicleType enum
- **PricingStrategyFactory** - Returns the correct PricingStrategy based on vehicle type
