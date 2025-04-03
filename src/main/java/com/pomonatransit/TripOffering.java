package com.pomonatransit;

public class TripOffering {
    private int tripNumber;
    private String date;
    private String scheduledStartTime;
    private String scheduledArrivalTime;
    private String driverId;
    private String busId;

    public TripOffering(int tripNumber, String date, String scheduledStartTime,
    String scheduledArrivalTime, String driverId, String busId){
        this.tripNumber = tripNumber;
        this.date = date;
        this.scheduledStartTime = scheduledStartTime;
        this.scheduledArrivalTime = scheduledArrivalTime;
        this.driverId = driverId;
        this.busId = busId;
    }

    public int getTripNumber() { return tripNumber; }
    public void setTripNumber(int tripNumber) { this.tripNumber = tripNumber; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getScheduledStartTime() { return scheduledStartTime; }
    public void setScheduledStartTime(String scheduledStartTime) { this.scheduledStartTime = scheduledStartTime; }

    public String getScheduledArrivalTime() { return scheduledArrivalTime; }
    public void setScheduledArrivalTime(String scheduledArrivalTime) { this.scheduledArrivalTime = scheduledArrivalTime; }

    public String getDriverId() { return driverId; }
    public void setDriverId(String driverId) { this.driverId = driverId; }

    public String getBusId() { return busId; }
    public void setBusId(String busId) { this.busId = busId; }

}
