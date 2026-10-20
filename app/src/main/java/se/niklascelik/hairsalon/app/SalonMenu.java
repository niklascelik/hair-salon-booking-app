
package se.niklascelik.hairsalon.app;

import java.util.Scanner;
import java.util.List;
import org.bookingscheduler.Slot;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Handles the menus and user input for the hair salon application.
 */
public class SalonMenu {

    private final Scanner scanner;
    private final SalonService salon;

    /**
     * Creates a menu using the given input scanner and salon service.
     *
     * @param scanner scanner used to read user input
     * @param salon salon service used for salon operations
     */
    public SalonMenu(Scanner scanner, SalonService salon) {
        this.scanner = scanner;
        this.salon = salon;
    }

    /**
     * Starts the main menu.
     */
    public void run() {
        clearScreen();

        boolean running = true;
        while (running) {

            System.out.println("=== HAIR SALON ===");
            System.out.println("1. Customer");
            System.out.println("2. Administrator");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    showCustomerMenu();
                    clearScreen();
                    break;

                case "2":
                    showAdminMenu();
                    clearScreen();
                    break;

                case "0":
                    System.out.println("Exit");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }

        }

    }

    /**
     * Displays the customer menu.
     */
    private void showCustomerMenu() {
        clearScreen();

        boolean running = true;
        while (running) {

            System.out.println("=== CUSTOMER MENU ===");
            System.out.println("1. View available times");
            System.out.println("2. Book an appointment");
            System.out.println("0. Back to main menu");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showAvailableTimes();
                    waitForEnter();
                    clearScreen();
                    break;

                case "2":
                    showBookingMenu();
                    waitForEnter();
                    clearScreen();
                    break;

                case "0":
                    System.out.println("Back to main menu placeholder");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option please try again.");
                    break;

            }

        }

    }

    /**
     * Displays the available appointment times for a selected service.
     */
    private void showAvailableTimes() {

        String scheduleName = selectService();

        if (scheduleName == null) {
            return;
        }

        clearScreen();

        List<Slot> availableSlots = salon.getAvailableSlots(scheduleName);

        if (availableSlots.isEmpty()) {
            System.out.println("No available appointments!");
            return;
        }

        for (Slot slot : availableSlots) {
            System.out.println("Slot " + slot.getSlotId()
                    + " | " + slot.getStartTime()
                    + " - " + slot.getEndTime());
        }

    }

    /**
     * Lets the user select a salon service.
     *
     * @return the selected service name, or {@code null} if the selection is invalid
     */
    private String selectService() {
        List<String> scheduleNames = salon.getScheduleNames();

        if (scheduleNames.isEmpty()) {
            System.out.println("No services available.");
            return null;
        }

        clearScreen();

        System.out.println("=== AVAILABLE SERVICES ===");
        for (int i = 0; i < scheduleNames.size(); i++) {
            System.out.println(i + 1 + "." + scheduleNames.get(i));
        }
        System.out.print("Select a service: ");
        String choice = scanner.nextLine();
        int selectedServiceNumber;
        try {
            selectedServiceNumber = Integer.parseInt(choice);

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number");
            return null;
        }

        if (selectedServiceNumber < 1 || selectedServiceNumber > scheduleNames.size()) {
            System.out.println("Please select a valid service");
            return null;

        }
        return scheduleNames.get(selectedServiceNumber - 1);
    }

    /**
     * Clears the console screen.
     */
    private void clearScreen() {
        System.out.print("\u001B[2J\u001B[3J\u001B[H");
        System.out.flush();
    }

    /**
     * Waits for the user to press Enter.
     */
    private void waitForEnter() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }

    /**
     * Displays the appointment booking menu and books an appointment.
     */
    private void showBookingMenu() {

        String scheduleName = selectService();

        if (scheduleName == null) {
            return;
        }

        List<Slot> availableSlots = salon.getAvailableSlots(scheduleName);

        if (availableSlots.isEmpty()) {
            System.out.println("No available appointments!");
            return;
        }

        clearScreen();

        System.out.println("=== BOOK AN APPOINTMENT ===");

        for (Slot slot : availableSlots) {
            System.out.println("Slot " + slot.getSlotId()
                    + " | " + slot.getStartTime()
                    + " - " + slot.getEndTime());
        }

        System.out.print("Enter slot ID: ");
        String slotIdInput = scanner.nextLine();

        clearScreen();

        System.out.print("Enter your name: ");
        String nameInput = scanner.nextLine();

        clearScreen();

        System.out.print("Enter your email: ");
        String emailInput = scanner.nextLine();

        clearScreen();

        try {
            salon.bookAppointment(scheduleName, slotIdInput, nameInput, emailInput);
            System.out.println("Appointment booked successfully!");

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }

    /**
     * Displays the administrator menu.
     */
    private void showAdminMenu() {

        while (true) {
            clearScreen();

            System.out.println("=== ADMINISTRATOR MENU ===");
            System.out.println("1. Create fixed schedule");
            System.out.println("2. Create flexible schedule");
            System.out.println("3. Add slot to flexible schedule");
            System.out.println("4. View bookings");
            System.out.println("5. Export booking statistics (SVG)");
            System.out.println("0. Back to main menu");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();

            if (choice.equals("0")) {
                return;
            }

            clearScreen();

            try {
                switch (choice) {
                    case "1":
                        createFixedSchedule();
                        break;

                    case "2":
                        createFlexibleSchedule();
                        break;

                    case "3":
                        addFlexibleSlot();
                        break;

                    case "4":
                        showBookings();
                        break;

                    case "5":
                        BookingStatisticsExporter exporter = new BookingStatisticsExporter(salon);
                        exporter.export();
                        System.out.println("Statistics exported successfully!");
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }

            } catch (IllegalArgumentException | IllegalStateException | DateTimeParseException e) {
                System.out.println("Error: " + e.getMessage());
            }

            waitForEnter();
        }
    }

    /**
     * Creates a fixed schedule from user input.
     */
    private void createFixedSchedule() {

        System.out.println("=== CREATE FIXED SCHEDULE ===");

        System.out.print("Schedule name: ");
        String scheduleName = scanner.nextLine();

        LocalTime openingTime = readTime("Opening time (HH:mm): ");
        LocalTime closingTime = readTime("Closing time (HH:mm): ");

        validateOpeningHours(openingTime, closingTime);

        System.out.print("Slot duration (minutes): ");
        int slotDuration = Integer.parseInt(scanner.nextLine().trim());

        salon.createFixedSchedule(
                scheduleName,
                openingTime,
                closingTime,
                slotDuration);

        System.out.println("Fixed schedule created successfully!");
    }

    /**
     * Creates a flexible schedule from user input.
     */
    private void createFlexibleSchedule() {

        System.out.println("=== CREATE FLEXIBLE SCHEDULE ===");

        System.out.print("Schedule name: ");
        String scheduleName = scanner.nextLine();

        LocalTime openingTime = readTime("Opening time (HH:mm): ");
        LocalTime closingTime = readTime("Closing time (HH:mm): ");

        validateOpeningHours(openingTime, closingTime);

        salon.createFlexibleSchedule(
                scheduleName,
                openingTime,
                closingTime);

        System.out.println("Flexible schedule created successfully!");
    }

    /**
     * Adds a time slot to a selected flexible schedule.
     */
    private void addFlexibleSlot() {

        String scheduleName = selectService();

        if (scheduleName == null) {
            return;
        }

        clearScreen();

        System.out.println("=== ADD FLEXIBLE SLOT ===");
        System.out.println("Schedule: " + scheduleName);

        LocalTime startTime = readTime("Start time (Hour:minute): ");
        LocalTime endTime = readTime("End time (Hour:minute): ");

        salon.addFlexibleSlot(scheduleName, startTime, endTime);

        System.out.println("Slot added successfully!");
    }

    /**
     * Displays the bookings for a selected service.
     */
    private void showBookings() {

        String scheduleName = selectService();

        if (scheduleName == null) {
            return;
        }

        clearScreen();

        System.out.println("=== BOOKINGS: " + scheduleName + " ===");

        List<Slot> bookedSlots = salon.getBookedSlots(scheduleName);

        if (bookedSlots.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        for (Slot slot : bookedSlots) {
            System.out.println(
                    "Slot " + slot.getSlotId()
                            + " | " + slot.getStartTime()
                            + " - " + slot.getEndTime());

            System.out.println(
                    "Customer: " + slot.getBooking().getCustomerName());

            System.out.println(
                    "Email: " + slot.getBooking().getCustomerEmail());

            System.out.println("-----------------------------");
        }
    }

    /**
     * Reads a time from the user.
     *
     * @param prompt message shown before reading the time
     * @return the entered time
     */
    private LocalTime readTime(String prompt) {
        System.out.print(prompt);
        return LocalTime.parse(scanner.nextLine().trim());
    }

    /**
     * Checks that the opening time is before the closing time.
     *
     * @param openingTime salon opening time
     * @param closingTime salon closing time
     * @throws IllegalArgumentException if the opening time is not before the closing time
     */
    private void validateOpeningHours(LocalTime openingTime, LocalTime closingTime) {
        if (!openingTime.isBefore(closingTime)) {
            throw new IllegalArgumentException(
                    "Opening time must be before closing time.");
        }
    }

}
