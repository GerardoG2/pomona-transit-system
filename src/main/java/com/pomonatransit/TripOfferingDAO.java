package com.pomonatransit;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.pomonatransit.TripOffering;

public interface TripOfferingDAO{
    void deleteTripOffering(int tripNumber, String dateStr, String scheduledStartTimeStr);
    void addTripOffering (TripOffering tripOffering);
    void updateDriver(int tripNumber, String date, String scheduledStartTime, String newDriverID);
    void updateBus(int tripNumber, String date, String scheduledStartTime, String newBusID);
    void dispDriverSchedule(String driverId, String startDateStr, String endDateStr);
}