# Hair Salon Booking System

## Vision

The Hair Salon Booking System is a Java console application designed to help small hair salons manage customer appointments.

The application allows administrators to create fixed and flexible schedules, manage customer bookings, and generate booking statistics. Customers can view available appointment times and book appointments.

The application uses two independent Java modules:
- **booking-scheduler** – Manages schedules, time slots, and bookings.
- **svg-charts-module** – Generates SVG charts for booking statistics.

## Functional Requirements

| ID | Requirement |
|---|---|
| R1 | The administrator shall be able to create fixed schedules by specifying a schedule name, opening time, closing time, and slot duration. |
| R2 | The administrator shall be able to create flexible schedules and manually add time slots with individual start and end times. |
| R3 | Customers shall be able to view available appointment slots for a selected schedule. |
| R4 | Customers shall be able to book an available appointment by providing their name and email address. |
| R5 | The application shall prevent double bookings by rejecting attempts to book an already occupied time slot. |
| R6 | The administrator shall be able to view existing customer bookings for a selected schedule. |
| R7 | The administrator shall be able to export booking statistics as an SVG bar chart showing the number of bookings per schedule or treatment. |

## Scope

The application will use a console based interface.

Bookings and schedules will initially be stored in memory and will not persist after the application is closed.

The administrator and customer functions will be accessible through separate menus without requiring user authentication.