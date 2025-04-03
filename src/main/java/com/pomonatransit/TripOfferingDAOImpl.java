package com.pomonatransit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.postgresql.core.SqlCommand;

import com.pomonatransit.TripOffering;

public class TripOfferingDAOImpl implements TripOfferingDAO{
    private Connection conn;


    public TripOfferingDAOImpl(Connection conn){
        this.conn = conn;
    }

    public void disp_trip_offering_schedule(){
        try {
            PreparedStatement ps = conn.prepareStatement("select * from trip_offering");
            ResultSet rs = ps.executeQuery();

            System.out.printf("\n%-8s %-12s %-12s %-12s %-10s %-8s\n",
            "Trip #", "Date", "Start Time", "Arrival Time", "Driver", "Bus");
            System.out.println("-------------------------------------------------------------");
            while(rs.next()){
                int tripNumber = rs.getInt("trip_number");
                String date = rs.getString("date");
                String scheduledStartTime = rs.getString("scheduled_start_time");
                String scheduledArrivalTime = rs.getString("scheduled_arrival_time");
                String driverId = rs.getString("driver_id");
                String busId = rs.getString("bus_id");

                System.out.printf("%-8d %-12s %-12s %-12s %-10s %-8s\n",
                tripNumber, date, scheduledStartTime, scheduledArrivalTime, driverId, busId);



            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        }
    @Override
    public void deleteTripOffering(int tripNumber, String dateStr, String scheduledStartTimeStr){
        try{
            LocalDate date = LocalDate.parse(dateStr);
            LocalTime scheduledStartTime = LocalTime.parse(scheduledStartTimeStr);
            PreparedStatement ps = conn.prepareStatement("DELETE FROM trip_offering WHERE"+
            " trip_number = ? AND date = ? AND scheduled_start_time = ?");
            ps.setInt(1, tripNumber);
            ps.setDate(2, Date.valueOf(date));
            ps.setTime(3, Time.valueOf(scheduledStartTime));

            int rows_affected = ps.executeUpdate();

            if (rows_affected >= 1){
                System.out.println("\nUpdated Schedule:\n");
                disp_trip_offering_schedule();
            } else {
                System.out.println("Trip offering not found.");
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void addTripOffering (TripOffering tripOffering){
        int tripNumber = tripOffering.getTripNumber();
        LocalDate date = tripOffering.getDate();
        LocalTime scheduledStartTime = tripOffering.getScheduledStartTime();
        LocalTime scheduledArrivalTime = tripOffering.getScheduledArrivalTime();
        String driverId = tripOffering.getDriverId();
        String busId = tripOffering.getBusId();
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO trip_offering (trip_number, date, scheduled_start_time, scheduled_arrival_time, driver_id, bus_id)" +
            " VALUES(?, ?, ?, ?, ?,?)");
            ps.setInt(1,tripNumber);
            ps.setDate(2, Date.valueOf(date));
            ps.setTime(3, Time.valueOf(scheduledStartTime));
            ps.setTime(4, Time.valueOf(scheduledArrivalTime));
            ps.setString(5,driverId);
            ps.setString(6,busId);
            ps.execute();

            System.out.println("Trip offering successfully added!");
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

    @Override
    public void updateDriver(int tripNumber, String date, String scheduledStartTime, String newDriverID){
        try{
            PreparedStatement ps = conn.prepareStatement("UPDATE trip_offering " +
            "SET driver_id = ? WHERE trip_number = ? AND date = ? AND scheduled_start_time = ?");
            ps.setString(1, newDriverID);
            ps.setInt(2, tripNumber);
            ps.setDate(3, Date.valueOf(date));
            ps.setTime(4, Time.valueOf(scheduledStartTime));

            ps.execute();

            System.out.println("Successfully updated the driver for trip offfering!");
        } catch (SQLException e){
            if (e.getSQLState().equals("23503")) {
                System.out.println("Foreign key violation.");
            } else {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateBus(int tripNumber, String date, String scheduledStartTime, String newBusID){
        try{
            PreparedStatement ps = conn.prepareStatement("UPDATE trip_offering " +
            "SET bus_id = ? WHERE trip_number = ? AND date = ? AND scheduled_start_time = ?");
            ps.setString(1, newBusID);
            ps.setInt(2, tripNumber);
            ps.setDate(3, Date.valueOf(date));
            ps.setTime(4, Time.valueOf(scheduledStartTime));

            ps.execute();

            System.out.println("Successfully updated the bus for trip offfering!");
        } catch (SQLException e){
            if (e.getSQLState().equals("23503")) {
                System.out.println("Foreign key violation.");
            } else {
                e.printStackTrace();
            }
        }

    }


    @Override
    public void dispDriverSchedule(String driverId, String startDateStr, String endDateStr){
        try{
            Date startDate = Date.valueOf(startDateStr);
            Date endDate = Date.valueOf(endDateStr);
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM trip_offering WHERE driver_id = ? AND date >= ? AND date <= ?");
            ps.setString(1, driverId);
            ps.setDate(2, startDate);
            ps.setDate(3, endDate);
            
            ResultSet rs = ps.executeQuery();
            System.out.printf("\n%-8s %-12s %-12s %-12s %-10s %-8s\n",
            "Trip #", "Date", "Start Time", "Arrival Time", "Driver", "Bus");
            System.out.println("-------------------------------------------------------------");
            while(rs.next()){
                int tripNumber = rs.getInt("trip_number");
                String date = rs.getString("date");
                String scheduledStartTime = rs.getString("scheduled_start_time");
                String scheduledArrivalTime = rs.getString("scheduled_arrival_time");
                String busId = rs.getString("bus_id");

                System.out.printf("%-8d %-12s %-12s %-12s %-10s %-8s\n",
                tripNumber, date, scheduledStartTime, scheduledArrivalTime, driverId, busId);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
            

    } 
}


    




