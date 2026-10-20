
package se.niklascelik.hairsalon.app;

import java.time.LocalTime;
import org.bookingscheduler.TimeTable;
import org.bookingscheduler.Slot;

public class App {

    public static void main(String[] args) {

        TimeTable schedule = new TimeTable(
                30,
                true,
                LocalTime.of(9, 0),
                LocalTime.of(12, 0)
        );

        System.out.println("=== HAIR SALON SCHEDULE ===");

        for (Slot slot : schedule.getSlots()) {
            System.out.println(
                    slot.getSlotId() + " | " +
                    slot.getStartTime() + " - " +
                    slot.getEndTime()
            );
        }
    }
}
