
package se.niklascelik.hairsalon.app;

import java.time.LocalTime;
import org.bookingscheduler.TimeTable;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.bookingscheduler.Slot;
import java.util.ArrayList;
import org.bookingscheduler.Booking;

/**
 * Manages salon schedules and appointments.
 */
public class SalonService {

    private final Map<String, TimeTable> schedules = new HashMap<>();

    /**
     * Creates a schedule with fixed-length time slots.
     *
     * @param scheduleName name of the schedule
     * @param openingTime schedule opening time
     * @param closingTime schedule closing time
     * @param slotDuration duration of each slot in minutes
     */
    public void createFixedSchedule(String scheduleName, LocalTime openingTime, LocalTime closingTime,
            int slotDuration) {

        validateScheduleName(scheduleName);
        TimeTable fixedSchedule = new TimeTable(slotDuration, true, openingTime, closingTime);

        schedules.put(scheduleName, fixedSchedule);

    }

    /**
     * Returns a schedule with the specified name.
     *
     * @param scheduleName name of the schedule
     * @return the requested schedule
     * @throws IllegalArgumentException if the schedule does not exist
     */
    public TimeTable getSchedule(String scheduleName) {
        if (!schedules.containsKey(scheduleName)) {
            throw new IllegalArgumentException("Schedule name does not exist!");
        }
        return schedules.get(scheduleName);
    }

    /**
     * Creates a schedule where time slots can be added manually.
     *
     * @param scheduleName name of the schedule
     * @param openingTime schedule opening time
     * @param closingTime schedule closing time
     */
    public void createFlexibleSchedule(String scheduleName, LocalTime openingTime, LocalTime closingTime) {

        validateScheduleName(scheduleName);
        TimeTable flexibleSchedule = new TimeTable(null, false, openingTime, closingTime);
        schedules.put(scheduleName, flexibleSchedule);
    }

    /**
     * Checks that a schedule name is valid and not already in use.
     *
     * @param scheduleName name to validate
     * @throws IllegalArgumentException if the name is empty or already exists
     */
    private void validateScheduleName(String scheduleName) {

        if (scheduleName == null || scheduleName.isBlank()) {
            throw new IllegalArgumentException("Schedule name must contain atleast one character.");
        }
        if (schedules.containsKey(scheduleName)) {
            throw new IllegalArgumentException("Please enter a unqique schedule name.");
        }

    }

    /**
     * Adds a time slot to a flexible schedule.
     *
     * @param scheduleName name of the schedule
     * @param startTime slot start time
     * @param endTime slot end time
     * @throws IllegalArgumentException if the schedule does not exist
     */
    public void addFlexibleSlot(String scheduleName, LocalTime startTime, LocalTime endTime) {
        TimeTable currentSchedule = getSchedule(scheduleName);

        currentSchedule.addSlot(startTime, endTime);

    }

    /**
     * Returns all available slots for a schedule.
     *
     * @param scheduleName name of the schedule
     * @return list of slots that are not booked
     * @throws IllegalArgumentException if the schedule does not exist
     */
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

    /**
     * Books an appointment in a selected slot.
     *
     * @param scheduleName name of the schedule
     * @param slotId ID of the slot to book
     * @param customerName name of the customer
     * @param customerEmail email address of the customer
     * @throws IllegalArgumentException if the schedule or slot does not exist
     */
    public void bookAppointment(String scheduleName, String slotId, String customerName, String customerEmail) {

        TimeTable currentSchedule = getSchedule(scheduleName);
        Slot selectedSlot = currentSchedule.getSlotById(slotId);
        if (selectedSlot == null) {
            throw new IllegalArgumentException("Slot doesnt exist.");
        }

        Booking booking = new Booking(customerName, customerEmail);
        selectedSlot.book(booking);

    }

    /**
     * Returns all booked slots for a schedule.
     *
     * @param scheduleName name of the schedule
     * @return list of booked slots
     * @throws IllegalArgumentException if the schedule does not exist
     */
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

    /**
     * Returns the names of all schedules.
     *
     * @return list of schedule names
     */
    public List<String> getScheduleNames() {
    return new ArrayList<>(schedules.keySet());
}
   

}