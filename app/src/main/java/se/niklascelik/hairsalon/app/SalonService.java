
package se.niklascelik.hairsalon.app;

import java.time.LocalTime;
import org.bookingscheduler.TimeTable;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.bookingscheduler.Slot;
import java.util.ArrayList;
import org.bookingscheduler.Booking;

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

    public void createFlexibleSchedule(String scheduleName, LocalTime openingTime, LocalTime closingTime) {

        validateScheduleName(scheduleName);
        TimeTable flexibleSchedule = new TimeTable(null, false, openingTime, closingTime);
        schedules.put(scheduleName, flexibleSchedule);
    }

    private void validateScheduleName(String scheduleName) {

        if (scheduleName == null || scheduleName.isBlank()) {
            throw new IllegalArgumentException("Schedule name must contain atleast one character.");
        }
        if (schedules.containsKey(scheduleName)) {
            throw new IllegalArgumentException("Please enter a unqique schedule name.");
        }

    }

    public void addFlexibleSlot(String scheduleName, LocalTime startTime, LocalTime endTime) {
        TimeTable currentSchedule = getSchedule(scheduleName);

        currentSchedule.addSlot(startTime, endTime);

    }

    public List<Slot> getAvailableSlots(String scheduleName) {
        List<Slot> availableSlots = new ArrayList<>();
        TimeTable currentSchedule = getSchedule(scheduleName);

        for (Slot slot : currentSchedule.getSlots()) {
            if (slot.getBooking() == null) {
                availableSlots.add(slot);
            }
        }
        return availableSlots;

    }

    public void bookAppointment(String scheduleName, String slotId, String customerName, String customerEmail) {

        TimeTable currentSchedule = getSchedule(scheduleName);
        Slot selectedSlot = currentSchedule.getSlotById(slotId);
        if (selectedSlot == null) {
            throw new IllegalArgumentException("Slot doesnt exist.");
        }

        Booking booking = new Booking(customerName, customerEmail);
        selectedSlot.book(booking);

    }

    public List<Slot> getBookedSlots(String scheduleName){
        List<Slot> bookedSlots = new ArrayList<>();

        TimeTable currentSchedule =  getSchedule(scheduleName);
        for (Slot slot : currentSchedule.getSlots()) {
            if (slot.getBooking() != null){
                bookedSlots.add(slot);
            } 
        }
        return bookedSlots;

    }

    public List<String> getScheduleNames() {
    return new ArrayList<>(schedules.keySet());
}
   

}