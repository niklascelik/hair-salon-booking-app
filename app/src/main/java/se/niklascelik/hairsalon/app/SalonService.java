
package se.niklascelik.hairsalon.app;

import java.time.LocalTime;
import org.bookingscheduler.TimeTable;
import java.util.HashMap;
import java.util.Map;

public class SalonService {

    private final Map<String, TimeTable> schedules = new HashMap<>();

    public void createFixedSchedule(String scheduleName, LocalTime openingTime, LocalTime closingTime,
            int slotDuration) {

        validateScheduleName(scheduleName);
        TimeTable fixedSchedule = new TimeTable(slotDuration, true, openingTime, closingTime);

        schedules.put(scheduleName, fixedSchedule);

    }

    public TimeTable getSchedule(String scheduleName) {
        if (!schedules.containsKey(scheduleName)) {
            throw new IllegalArgumentException("Schedule name does not exist!");
        }
        return schedules.get(scheduleName);
    }

    public void createFlexibleSchedule(String scheduleName, LocalTime openingTime, LocalTime closingTime,
            Integer slotDuration) {

        validateScheduleName(scheduleName);
        if (slotDuration != null) {
            throw new IllegalArgumentException("Please dont enter a slot duration for flexible schedules");
        }
        TimeTable flexibleSchedule = new TimeTable(null, false, openingTime, closingTime);
        schedules.put(scheduleName, flexibleSchedule);
    }

    public void validateScheduleName(String scheduleName) {
        if (schedules.containsKey(scheduleName)) {
            throw new IllegalArgumentException("Please enter a unqique schedule name.");
        }
        if (scheduleName == null || scheduleName.isBlank()) {
            throw new IllegalArgumentException("Schedule name must contain atleast one character.");
        }

    }

}