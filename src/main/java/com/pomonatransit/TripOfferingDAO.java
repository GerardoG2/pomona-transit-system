package com.pomonatransit;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.pomonatransit.TripOffering;

public interface TripOfferingDAO{
    void deleteTripOffering(int tripNumber, LocalDate date, LocalTime scheduledStartTime);
    void addTripOffering (TripOffering tripOffering);
    void updateDriver(int tripNumber, LocalDate date, LocalTime scheduledStartTime, String newDrivername, String newDriverID);
    void updateBus(int tripNumber, LocalDate date, LocalTime scheduledStartTime, String newBusID);
    List<TripOffering> getTripSchedule (String startLocation, String destination, LocalDate date);
}