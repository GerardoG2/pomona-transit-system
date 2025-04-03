package com.pomonatransit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
public class ActualTripStopInfoDAOImpl implements ActualTripStopInfoDAO{
    private Connection conn;

    public ActualTripStopInfoDAOImpl(Connection conn){
        this.conn = conn;
    }

    @Override
    public void dispActualTripStopInfo(){
        try {
            PreparedStatement ps = conn.prepareStatement("select * from actual_trip_stop_info");
            ResultSet rs = ps.executeQuery();

            System.out.printf("\n%-8s %-12s %-20s %-12s %-25s %-20s %-15s %-15s\n",
            "Trip #", "Date", "Scheduled Start Time", "Stop Number" , " Scheduled Arrival Time","Actual Start Time", "Passengers In", "Passengers Out");
            System.out.println("-------------------------------------------------------------");
            while(rs.next()){
                int tripNumber = rs.getInt("trip_number");
                String date = rs.getString("date");
                String scheduledStartTime = rs.getString("scheduled_start_time");
                int stopNumber = rs.getInt("stop_number");
                String scheduledArrivalTime = rs.getString("scheduled_arrival_time");
                String actualStartTime = rs.getString("actual_start_time");
                int numberOfPassengersIn= rs.getInt("number_of_passengers_in");
                int numberOfPassengersOut = rs.getInt("number_of_passengers_out");

                System.out.printf("%-8d %-12s %-20s %-12d %-25s %-20s %-15d %-15d\n",
                tripNumber, date, scheduledStartTime, stopNumber, scheduledArrivalTime, actualStartTime, numberOfPassengersIn, numberOfPassengersOut);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertActualTripStopInfo(ActualTripStopInfo actualTripStopInfo){
        int tripNumber = actualTripStopInfo.getTripNumber();
        Date date = Date.valueOf(actualTripStopInfo.getDate());
        Time scheduledStartTime = Time.valueOf(actualTripStopInfo.getScheduledStartTime());
        int stopNumber = actualTripStopInfo.getStopNumber();
        Time scheduledArrivalTime = Time.valueOf(actualTripStopInfo.getScheduledArrivalTime());
        Time actualStartTime = Time.valueOf(actualTripStopInfo.getActualStartTime());
        int numberOfPassengersIn = actualTripStopInfo.getNumberOfPassengersIn();
        int numberOfPassengersOut = actualTripStopInfo.getNumberOfPassengersOut();
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO actual_trip_stop_info (trip_number, date, scheduled_start_time, stop_number," +
            " scheduled_arrival_time, actual_start_time, number_of_passengers_in, number_of_passengers_out)" +
            " VALUES(?, ?, ?, ?, ?,?, ?, ?)");
            ps.setInt(1,tripNumber);
            ps.setDate(2, date);
            ps.setTime(3, scheduledStartTime);
            ps.setInt(4, stopNumber);
            ps.setTime(5, scheduledArrivalTime);
            ps.setTime(6, actualStartTime);
            ps.setInt(7,numberOfPassengersIn);
            ps.setInt(8,numberOfPassengersOut);
            ps.execute();

            System.out.println("Actual trip info successfully inserted!");
            // System.out.println("Would you like to add more trip offerings? Enter yes or no.");
            // does not need to be inside method 

        } catch (SQLException e){
            if (e.getSQLState().equals("23503")) {
                System.out.println("Foreign key violation.");
            } else {
                e.printStackTrace();
            }
        }

    }
    
}
