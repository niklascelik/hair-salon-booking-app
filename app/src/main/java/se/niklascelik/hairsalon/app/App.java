
package se.niklascelik.hairsalon.app;

import java.time.LocalTime;
import org.bookingscheduler.TimeTable;
import org.bookingscheduler.Slot;

public class App {

    public static void main(String[] args) {

        SalonService salon = new SalonService();

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

        TimeTable schedule = salon.getSchedule("Hair coloring");

        System.out.println("=== HAIR COLORING SCHEDULE ===");

        for (Slot slot : schedule.getSlots()) {
            System.out.println(
                    slot.getSlotId() + " | " +
                    slot.getStartTime() + " - " +
                    slot.getEndTime()
            );
        }
    }
}
