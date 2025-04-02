package com.pomonatransit;

import java.time.LocalDate;
import java.time.LocalTime;

public class TripOffering {
    private int tripNumber;
    private LocalDate date;
    private LocalTime scheduledStartTime;
    private LocalTime scheduledArrivalTime;
    private String driverId;
    private String busId;

    public TripOffering(int tripNumber, String date, String scheduledStartTime,
    String scheduledArrivalTime, String driverId, String busId){
        this.tripNumber = tripNumber;
        this.date = LocalDate.parse(date);
        this.scheduledStartTime = LocalTime.parse(scheduledStartTime);
        this.scheduledArrivalTime = LocalTime.parse(scheduledArrivalTime);
        this.driverId = driverId;
        this.busId = busId;
    }

    public int getTripNumber() { return tripNumber; }
    public void setTripNumber(int tripNumber) { this.tripNumber = tripNumber; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getScheduledStartTime() { return scheduledStartTime; }
    public void setScheduledStartTime(LocalTime scheduledStartTime) { this.scheduledStartTime = scheduledStartTime; }

    public LocalTime getScheduledArrivalTime() { return scheduledArrivalTime; }
    public void setScheduledArrivalTime(LocalTime scheduledArrivalTime) { this.scheduledArrivalTime = scheduledArrivalTime; }

    public String getDriverId() { return driverId; }
    public void setDriverId(String driverId) { this.driverId = driverId; }

    public String getBusId() { return busId; }
    public void setBusId(String busId) { this.busId = busId; }

}
