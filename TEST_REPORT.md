## Test Report – Hair Salon Booking System

### Testing Method

The application was tested manually through its console interface. Each test was performed by entering values through the customer or administrator menus and comparing the results with the expected behavior.

### Test Cases

| Test Case | Requirement | Input | Expected Result | Actual Result |
|---|---|---|---|---|
| TC1 – Create fixed schedule | R1 | Name: Haircuts, opening: 09:00, closing: 12:00, duration: 30 minutes | A schedule is created with six time slots | A schedule with six times is created.|
| TC2 – Create flexible schedule | R2 | Name: Hair Coloring, opening: 09:00, closing: 17:00; add slot 10:00–12:00 | The manually added slot is available | The slot is available. |
| TC3 – View available times | R3 | Select Haircuts | Six available slots are displayed before any bookings | The six slots are displayed. |
| TC4 – Book appointment | R4 | Haircuts, slot 1, Anna, anna@example.com | Booking succeeds and slot 1 disappears from available times |The booking sucessfully dissappears from the available slots.|
| TC5 – Prevent double booking | R5 | Attempt to book Haircuts slot 1 again | Booking is rejected and the application continues running | The booking is rejected with the message "Slot is already booked." |
| TC6 – View bookings | R6 | Administrator selects Haircuts under View bookings | Anna's appointment appears with customer information | The appointment appears with customer infromation(Name and Email)|
| TC7 – Export SVG statistics | R7 | Select Export booking statistics after creating bookings | An SVG file is generated and shows the correct number of bookings per service | The SVG file is generated and shows the correct number of bookings per service (2 haircuts and 1 hair coloring.).|

### Test Summary

I tested the application manually and using the console interface. Both customer and admin menus were used to test the functional requirments.

The tests covered creating fixed and flexible schedules, displaying available times, booking appointments, preventing double bookings, viewing customer bookings and information and exporting boking stats as a SVG file.

All of my 7 tests produced the expected results.

