package com.pomonatransit;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.pomonatransit.TripOffering;

public interface TripOfferingDAO{
    void dispTripOfferingSchedule();
    void deleteTripOffering();
    void addTripOffering();
    void updateDriver(int tripNumber, String date, String scheduledStartTime, String newDriverID);
    void updateBus(int tripNumber, String date, String scheduledStartTime, String newBusID);
    void dispDriverSchedule(String driverId, String startDateStr, String endDateStr);
}