# Hair Salon Booking System

## Vision

The Hair Salon Booking System is a Java console application designed to help small hair salons manage customer appointments.

The application allows administrators to create fixed and flexible schedules, manage customer bookings, and generate booking statistics. Customers can view available appointment times and book appointments.

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

## Requirements to Run

- Java JDK 25
- Git
- Internet connection for downloading gradle dependencies

The project includes the Gradle Wrapper so Gradle does not need to be installed separately.

## Installation

clone the repository with its Git submodule:

```bash
git clone --recurse-submodules https://github.com/niklascelik/hair-salon-booking-app.git
cd hair-salon-booking-app
```

The `--recurse-submodules` option is important because the application uses my booking-scheduler module as a Git submodule.

## Running the Application

Run the following command from the project root:

```bash
./gradlew :app:run --console=plain --quiet --no-configuration-cache
```

The console application will start and display the main menu:

```text
=== HAIR SALON ===
1. Customer
2. Administrator
0. Exit
Select an option:
```

The `--console=plain` and `--quiet` options help prevent Gradle's output from interfering with the interactive console menu.

## How to Use

### Administrator

Start by selecting `2. Administrator` from the main menu.

The administrator can:

- Create a fixed schedule that automatically generates appointment slots.
- Create a flexible schedule and manually add individual time slots.
- View customer bookings for a selected service.
- Export booking statistics as an SVG file.

For example, a fixed schedule from `09:00` to `12:00` with a slot duration of `30` minutes creates 6 appointment slots.

Times should be entered in `Hour:minute` format.

### Customer

Select `1. Customer` from the main menu.

Customers can:

- View the available services and appointment times.
- Select an available time slot.
- Book an appointment by entering their name and email address.

After a successful booking, the selected time slot is no longer shown as available.

### SVG Statistics

The administrator can select `5. Export booking statistics (SVG)`.

The application generates a file named `booking-statistics.svg`, containing a bar chart showing the number of bookings per service.

When running through Gradle, the file is saved in the `app` directory. It can be opened in a web browser.

## Modules

This application uses two independent Java modules.

### Booking Scheduler (My Module from L2)

Repository: https://github.com/niklascelik/booking-scheduler

This module handles schedules, appointment slots, bookings, and booking validation. It is included as a Git submodule and connected using Gradle.

### SVG Charts (Another Students Module)

Repository: https://github.com/Robert060615/svg-charts-module

This module generates SVG charts. The application uses its `BarChart` class to visualize booking statistics.

It is included as a Gradle dependency through jitpack.

The modules are independent of the application.

## Limitations

- Schedules and bookings are stored in memory.
- All stored data is lost when the application is closed.
- There is no authentication system for customers or administrators.
- The application uses a console interface and does not require a web server.

## Testing

The application's functional requirements are tested through the console interface.

Manual test cases, including test input, expected results, and actual results, are documented in `TEST_REPORT.md`.

The project can be compiled using:

```bash
./gradlew :app:compileJava
```