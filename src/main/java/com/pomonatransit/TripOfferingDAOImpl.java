package com.pomonatransit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class TripOfferingDAOImpl implements TripOfferingDAO{
    private Connection conn;


    public TripOfferingDAOImpl(Connection conn){
        this.conn = conn;
    }
    @Override
    public void dispTripOfferingSchedule(){
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
    public void deleteTripOffering(){
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter the trip number of the trip offering you would like to delete: ");
        int tripNumber = scnr.nextInt();
        System.out.println("Enter the date of the trip offering you would like to delete:  ");
        String date = scnr.nextLine();
        System.out.println("Enter the time(hh:mm:ss) of the trip offering you would like to delete:  ");
        String scheduledStartTime = scnr.nextLine();
        scnr.close();
        deleteTripOffering(tripNumber, date, scheduledStartTime);

    }

    
    private void deleteTripOffering(int tripNumber, String dateStr, String scheduledStartTimeStr){
        try{
            Date date = Date.valueOf(dateStr);
            Time scheduledStartTime = Time.valueOf(scheduledStartTimeStr);
            PreparedStatement ps = conn.prepareStatement("DELETE FROM trip_offering WHERE"+
            " trip_number = ? AND date = ? AND scheduled_start_time = ?");
            ps.setInt(1, tripNumber);
            ps.setDate(2, date);
            ps.setTime(3, scheduledStartTime);

            int rows_affected = ps.executeUpdate();

            if (rows_affected >= 1){
                System.out.println("\nUpdated Schedule:\n");
                dispTripOfferingSchedule();
            } else {
                System.out.println("Trip offering not found.");
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void addTripOffering(){
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter the following details of the new trip offering.\n");
        System.out.println("Trip Number: ");
        int tripNumber = scnr.nextInt();
        System.out.println("Date: ");
        String date = scnr.nextLine();
        System.out.println("Scheduled Start Time: ");
        String scheduledStartTime = scnr.nextLine();
        System.out.println("Scheduled Arrival Time: ");
        String scheduledArrivalTime = scnr.nextLine();
        System.out.println("Driver ID: ");
        String driverId = scnr.nextLine();
        System.out.println("Bus ID: ");
        String busId = scnr.nextLine();
        scnr.close();

        TripOffering tripOffering = new TripOffering(tripNumber, date, scheduledStartTime, scheduledArrivalTime, driverId, busId);
        addTripOffering(tripOffering);
    }
    
    private void addTripOffering (TripOffering tripOffering){
        int tripNumber = tripOffering.getTripNumber();
        Date date = Date.valueOf(tripOffering.getDate());
        Time scheduledStartTime = Time.valueOf(tripOffering.getScheduledStartTime());
        Time scheduledArrivalTime = Time.valueOf(tripOffering.getScheduledArrivalTime());
        String driverId = tripOffering.getDriverId();
        String busId = tripOffering.getBusId();
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO trip_offering (trip_number, date, scheduled_start_time, scheduled_arrival_time, driver_id, bus_id)" +
            " VALUES(?, ?, ?, ?, ?,?)");
            ps.setInt(1,tripNumber);
            ps.setDate(2, date);
            ps.setTime(3, scheduledStartTime);
            ps.setTime(4, scheduledArrivalTime);
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
    public void updateDriver(){
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter the trip number of the trip offering you would like modify: ");
        int tripNumber = scnr.nextInt();
        System.out.println("Enter the date of the trip offering you would like modify: ");
        String date = scnr.nextLine();
        System.out.println("Enter the scheduled start time of the trip offering you would like modify: ");
        String scheduledStartTime = scnr.nextLine();
        System.out.println("Enter the Driver ID of the new driver for this trip offering: ");
        String driverId = scnr.nextLine();
        scnr.close();

        updateDriver(tripNumber, date, scheduledStartTime, driverId);
    }

    
    private void updateDriver(int tripNumber, String date, String scheduledStartTime, String newDriverID){
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
    public void updateBus(){
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter the trip number of the trip offering you would like modify: ");
        int tripNumber = scnr.nextInt();
        System.out.println("Enter the date of the trip offering you would like modify: ");
        String date = scnr.nextLine();
        System.out.println("Enter the scheduled start time of the trip offering you would like modify: ");
        String scheduledStartTime = scnr.nextLine();
        System.out.println("Enter the Bus ID of the new bus for this trip offering: ");
        String busId = scnr.nextLine();
        scnr.close();

        updateBus(tripNumber, date, scheduledStartTime, busId);
    }

    
    private void updateBus(int tripNumber, String date, String scheduledStartTime, String newBusID){
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


    




