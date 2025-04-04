package com.pomonatransit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.util.Scanner;

public class TripOfferingDAOImpl implements TripOfferingDAO{
    private Connection conn;


    public TripOfferingDAOImpl(Connection conn){
        this.conn = conn;
    }

    @Override
    public void dispTripOffering(Scanner scnr){
        System.out.println("\nEnter the start location name of your desired trip: ");
        String startLocationName = scnr.nextLine();
        System.out.println("Enter the destination name of your desired trip: ");
        String destinationName = scnr.nextLine();

        dispTripOffering(startLocationName, destinationName);
    }

    /**
     * Displays trip offerings between the given start and destination locations.
     * Outputs trip number, date, scheduled times, driver ID, and bus ID.
     *
     * @param startLocationName Start location to match (case-insensitive)
     * @param destinationName Destination location to match (case-insensitive)
     */
    private void dispTripOffering(String startLocationName, String destinationName){
        try {
            PreparedStatement ps = conn.prepareStatement(
            "SELECT DISTINCT trip_offering.trip_number, date, scheduled_start_time, scheduled_arrival_time, driver_id, bus_id, start_location_name, destination_name "+
            "FROM trip_offering INNER JOIN trip ON trip_offering.trip_number = trip.trip_number WHERE start_location_name ILIKE ? AND destination_name ILIKE ?");   
            ps.setString(1, startLocationName );
            ps.setString(2, destinationName );
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                System.out.printf("\n%-8s %-12s %-40s %-40s %-12s %-12s %-10s %-8s\n",
                "Trip #", "Date", "Start Location", "Destination", "Start Time", "Arrival Time", "Driver", "Bus");
                System.out.println("-".repeat(150));
                Boolean hasNextRow = true;
                while(hasNextRow){
                    int tripNumber = rs.getInt("trip_number");
                    String date = rs.getString("date");
                    String scheduledStartTime = rs.getString("scheduled_start_time");
                    String scheduledArrivalTime = rs.getString("scheduled_arrival_time");
                    String driverId = rs.getString("driver_id");
                    String busId = rs.getString("bus_id");
    
                    System.out.printf("%-8d %-12s %-40s %-40s %-12s %-12s %-10s %-8s\n",
                    tripNumber, date,startLocationName, destinationName, scheduledStartTime, scheduledArrivalTime, driverId, busId);
                    hasNextRow = rs.next();
                } 
            } else { System.out.println("\nNo trip offerings found.");}

        } catch(SQLException e){
            e.printStackTrace();
        }
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
    public void deleteTripOffering(Scanner scnr){
        System.out.println("Enter the trip number of the trip offering you would like to delete: ");
        int tripNumber = scnr.nextInt();
        scnr.nextLine();
        System.out.println("Enter the date (YYYY:MM:DD) of the trip offering you would like to delete:  ");
        String date = scnr.nextLine();
        System.out.println("Enter the time (hh:mm:ss) of the trip offering you would like to delete:  ");
        String scheduledStartTime = scnr.nextLine();
        deleteTripOffering(tripNumber, date, scheduledStartTime);

    }

    /**
     * Deletes a trip offering based on its trip number, date, and scheduled start time.
     * If a record is deleted, it refreshes the trip offering schedule.
     *
     * @param tripNumber Trip number to delete
     * @param dateStr Date of the trip in YYYY-MM-DD format
     * @param scheduledStartTimeStr Scheduled start time of the trip in HH:mm:ss format
     */
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
    public void addTripOffering(Scanner scnr){
        System.out.println("Enter the following details of the new trip offering.\n");
        System.out.println("Trip Number: ");
        int tripNumber = scnr.nextInt();
        scnr.nextLine();
        System.out.println("Date (YYYY-MM-DD): ");
        String date = scnr.nextLine();
        System.out.println("Scheduled Start Time (hh:mm:ss): ");
        String scheduledStartTime = scnr.nextLine();
        System.out.println("Scheduled Arrival Time (hh:mm:ss): ");
        String scheduledArrivalTime = scnr.nextLine();
        System.out.println("Driver ID: ");
        String driverId = scnr.nextLine();
        System.out.println("Bus ID: ");
        String busId = scnr.nextLine();

        TripOffering tripOffering = new TripOffering(tripNumber, date, scheduledStartTime, scheduledArrivalTime, driverId, busId);
        addTripOffering(tripOffering);
    }
    
    /**
     * Inserts a new trip offering into the database using the provided TripOffering object.
     * Handles foreign key constraint violations gracefully.
     *
     * @param tripOffering Trip offering object containing all required data
     */
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

        } catch (SQLException e){
            if (e.getSQLState().equals("23503")) {
                System.out.println("Foreign key violation.");
            } else {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void updateDriver(Scanner scnr){
        System.out.println("Enter the trip number of the trip offering you would like modify: ");
        int tripNumber = scnr.nextInt();
        scnr.nextLine();
        System.out.println("Enter the date (YYY-MM-DD)of the trip offering you would like modify: ");
        String date = scnr.nextLine();
        System.out.println("Enter the scheduled start time (hh:mm:ss) of the trip offering you would like modify: ");
        String scheduledStartTime = scnr.nextLine();
        System.out.println("Enter the Driver ID of the new driver for this trip offering: ");
        String driverId = scnr.nextLine();

        updateDriver(tripNumber, date, scheduledStartTime, driverId);
    }

    /**
     * Updates the driver ID for a specific trip offering.
     * Trip offering is identified by trip number, date, and scheduled start time.
     *
     * @param tripNumber Trip number to modify
     * @param date Trip date in YYYY-MM-DD format
     * @param scheduledStartTime Scheduled start time in HH:mm:ss format
     * @param newDriverID New driver ID to assign
     */
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
    public void updateBus(Scanner scnr){
        System.out.println("Enter the trip number of the trip offering you would like modify: ");
        int tripNumber = scnr.nextInt();
        scnr.nextLine();
        System.out.println("Enter the date (YYYY-MM-DD) of the trip offering you would like modify: ");
        String date = scnr.nextLine();
        System.out.println("Enter the scheduled start time (hh:mm:ss) of the trip offering you would like modify: ");
        String scheduledStartTime = scnr.nextLine();
        System.out.println("Enter the Bus ID of the new bus for this trip offering: ");
        String busId = scnr.nextLine();

        updateBus(tripNumber, date, scheduledStartTime, busId);
    }

    /**
     * Updates the bus ID for a specific trip offering.
     * Trip offering is identified by trip number, date, and scheduled start time.
     *
     * @param tripNumber Trip number to modify
     * @param date Trip date in YYYY-MM-DD format
     * @param scheduledStartTime Scheduled start time in HH:mm:ss format
     * @param newBusID New bus ID to assign
     */
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




}


    




