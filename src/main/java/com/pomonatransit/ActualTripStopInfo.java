package com.pomonatransit;

public class ActualTripStopInfo {
    private int tripNumber;
    private String date;
    private String scheduledStartTime;
    private int stopNumber;
    private String scheduledArrivalTime;
    private String actualStartTime;
    private int numberOfPassengersIn;
    private int numberOfPassengersOut;

    public ActualTripStopInfo(int tripNumber, String date, String scheduledStartTime, int stopNumber, String scheduledArrivalTime,
                                String actualStartTime, int numberOfPassengersIn, int numberOfPassengersOut){
                                    this.tripNumber = tripNumber;
                                    this.date = date;
                                    this.scheduledStartTime = scheduledStartTime;
                                    this.stopNumber = stopNumber;
                                    this.scheduledArrivalTime = scheduledArrivalTime;
                                    this.actualStartTime = actualStartTime;
                                    this.numberOfPassengersIn = numberOfPassengersIn;
                                    this.numberOfPassengersOut = numberOfPassengersOut;

                                }
    
    public int getTripNumber(){return this.tripNumber;}
    
    public String getDate(){return this.date;}

    public String getScheduledStartTime(){return this.scheduledStartTime;}

    public int getStopNumber(){return this.stopNumber;}

    public String getScheduledArrivalTime(){return this.scheduledArrivalTime;}

    public String getActualStartTime(){return this.actualStartTime;}

    public int getNumberOfPassengersIn(){return this.numberOfPassengersIn;}

    public int getNumberOfPassengersOut(){return this.numberOfPassengersOut;}

    


    
}
