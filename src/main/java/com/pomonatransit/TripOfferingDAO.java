package com.pomonatransit;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.pomonatransit.TripOffering;

public interface TripOfferingDAO{
    void dispTripOfferingSchedule();
    void deleteTripOffering();
    void addTripOffering();
    void updateDriver();
    void updateBus();
    void dispDriverSchedule(String driverId, String startDateStr, String endDateStr);
}