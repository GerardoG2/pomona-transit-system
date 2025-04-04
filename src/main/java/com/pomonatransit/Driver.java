package com.pomonatransit;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.ResultSet;

public class Driver {
    private Connection conn;
    private String driverId;
    private String driverName;
    private String driverPhone;


    public Driver(Connection conn){
        this.conn = conn;
    }

    /**
     * Constructs a Driver object with the specified ID, name, and phone number.
     *
     * @param driverID Unique identifier for the driver
     * @param driverNAME Full name of the driver
     * @param driverPHONE Phone number in the format (###)###-####
     */
    public Driver(String driverID, String driverNAME, String driverPHONE){
        setDriverId(driverID);
        setDriverName(driverNAME);
        setDriverPhone(driverPHONE);
    }

    /**
     * Prompts the user to enter a new driver's ID, name, and phone number,
     * constructs a Driver object, and inserts it into the database.
     *
     * @param conn Active database connection
     * @param scnr Scanner used to capture user input
     */
    public static void addDriver(Connection conn, Scanner scnr){
        System.out.println("Enter the driver ID of the driver you would like to add: ");
        String driverId = scnr.nextLine();
        System.out.println("Enter the name of the driver you would like to add: ");
        String driverName = scnr.nextLine();
        System.out.println("Enter the phone number[(###)###-####] of the driver you would like to add: ");
        String driverPhone = scnr.nextLine();

        Driver driver = new Driver(driverId, driverName, driverPhone);
        driver.setDBConnection(conn);
        driver.addDriver(driver);

    }

    /**
     * Inserts the given Driver object into the driver table in the database.
     *
     * @param driver The driver to insert
     */
    private void addDriver(Driver driver){
        String newDriverId = driver.getDriverId();
        String newDriverName = driver.getDriverName();
        String newDriverPhone = driver.getDriverPhone();
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO driver(driver_id, driver_name, driver_phone_number) VALUES (?, ?, ?)");
            ps.setString(1, newDriverId);
            ps.setString(2, newDriverName);
            ps.setString(3, newDriverPhone);
            ps.execute();

            System.out.println("Driver successfully added!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Deletes a driver from the database using the given driver ID.
     *
     * @param driverID Unique identifier of the driver to be removed
     */
    public void deleteDriver(String driverID){
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM driver WHERE driver_id =?");
            ps.setString(1,driverID);
            ps.execute();
            System.out.println("Successfully removed " + driverID + " from driver table.");
        } catch(SQLException e){

        }
    }

    public String getDriverId(){return this.driverId; }
    public void setDriverId(String newDriverId){this.driverId = newDriverId;}

    public String getDriverName(){return this.driverName; }
    public void setDriverName(String newDriverName){this.driverName = newDriverName;}

    public String getDriverPhone(){return this.driverPhone; }
    public void setDriverPhone(String newDriverPhone){this.driverPhone = newDriverPhone;}

    public void setDBConnection(Connection conn){
        this.conn = conn;
    }

    /**
     * Displays all drivers from the driver table in a formatted table.
     * Each row includes driver ID, name, and phone number.
     */
    public void dispDrivers(){
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM driver");
            ResultSet rs = ps.executeQuery();
            System.out.printf("\n%-12s %-25s %-15s\n",
            "Driver ID", "Name", "Phone");
            System.out.println("-------------------------------------------------------------");
            while(rs.next()){
                String driverID = rs.getString("driver_id");
                String driverNAME = rs.getString("driver_name");
                String driverPHONE = rs.getString("driver_phone_number");

                System.out.printf("%-12s %-25s %-15s\n",
                driverID, driverNAME, driverPHONE);

            }


        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Prompts the user to enter a driver ID and a date range, then displays
     * the driver's trip schedule within that range.
     *
     * @param conn Active database connection
     * @param scnr Scanner used to collect user input
     */
    public static void dispDriverSchedule(Connection conn, Scanner scnr){
        System.out.println("Enter the driver ID for the driver's schedule you would like to view: ");
        String driverId = scnr.nextLine();
        System.out.println("Enter the start date (YYYY-MM-DD) for the schedule you would like to view: ");
        String startDate = scnr.nextLine();
        System.out.println("Enter the end date (YYYY-MM-DD) for the schedule you would like to view: ");
        String endDate = scnr.nextLine();

        dispDriverSchedule(conn, driverId, startDate, endDate);
    }

        /**
     * Displays a driver's trip schedule between the specified start and end dates.
     * Includes trip number, date, start time, arrival time, driver ID, and bus ID.
     *
     * @param conn Active database connection
     * @param driverId ID of the driver whose schedule is to be displayed
     * @param startDateStr Start date in YYYY-MM-DD format
     * @param endDateStr End date in YYYY-MM-DD format
     */
    private static void dispDriverSchedule(Connection conn, String driverId, String startDateStr, String endDateStr){
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
