
package se.niklascelik.hairsalon.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import se.lnu.rm222xi.svgcharts.BarChart;

public class BookingStatisticsExporter {

    private final SalonService salon;

    public BookingStatisticsExporter(SalonService salon) {
        this.salon = salon;
    }

    public void export() {

        List<String> scheduleNames = salon.getScheduleNames();
        List<Double> bookingCounts = new ArrayList<>();

        if (scheduleNames.isEmpty()) {
            throw new IllegalStateException("No schedules available.");
        }

        for (String scheduleName : scheduleNames) {
            int bookings = salon.getBookedSlots(scheduleName).size();
            bookingCounts.add((double) bookings);
        }

        BarChart chart = new BarChart();
        chart.setTitle("Bookings per service");
        chart.setLabels(scheduleNames);
        chart.setValues(bookingCounts);

        try {
            Files.writeString(Path.of("booking-statistics.svg"), chart.toSvg());

        } catch (IOException e) {
            throw new IllegalStateException("Could not save SVG file.", e);
        }
    }
}
