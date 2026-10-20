
package se.niklascelik.hairsalon.app;

import java.util.Scanner;
import java.util.List;
import org.bookingscheduler.Slot;

public class SalonMenu {

    private final Scanner scanner;
    private final SalonService salon;

    public SalonMenu(Scanner scanner, SalonService salon) {
        this.scanner = scanner;
        this.salon = salon;
    }

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
                    System.out.println("Admin");
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
                    System.out.println("Book an appointment placeholder");
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

    private void clearScreen() {
        System.out.print("\u001B[2J\u001B[3J\u001B[H");
        System.out.flush();
    }

    private void waitForEnter() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
}
