package com.pomonatransit;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.pomonatransit.TripOffering;

public interface TripOfferingDAO{
    void deleteTripOffering(int tripNumber, String dateStr, String scheduledStartTimeStr);
    void addTripOffering (TripOffering tripOffering);
    void updateDriver(int tripNumber, String date, String scheduledStartTime, String newDriverID);
    void updateBus(int tripNumber, LocalDate date, LocalTime scheduledStartTime, String newBusID);
    List<TripOffering> getTripSchedule (String startLocation, String destination, LocalDate date);
}