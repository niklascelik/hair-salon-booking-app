
package se.niklascelik.hairsalon.app;

import java.time.LocalTime;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        SalonService salon = new SalonService();

        // Test schedule 1: Fixed appointments
        salon.createFixedSchedule(
                "Haircuts",
                LocalTime.of(9, 0),
                LocalTime.of(12, 0),
                30
        );

        // Test schedule 2: Flexible appointments
        salon.createFlexibleSchedule(
                "Hair Coloring",
                LocalTime.of(9, 0),
                LocalTime.of(17, 0)
        );

        salon.addFlexibleSlot(
                "Hair Coloring",
                LocalTime.of(10, 0),
                LocalTime.of(12, 0)
        );

        salon.addFlexibleSlot(
                "Hair Coloring",
                LocalTime.of(13, 0),
                LocalTime.of(15, 0)
        );

        // Book one appointment to verify it disappears
        // from the available slots
        salon.bookAppointment(
                "Haircuts",
                "2",
                "Anna",
                "anna@example.com"
        );

        Scanner scanner = new Scanner(System.in);
        SalonMenu menu = new SalonMenu(scanner, salon);

        menu.run();
    }
}
