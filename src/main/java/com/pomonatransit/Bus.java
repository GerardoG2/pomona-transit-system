package com.pomonatransit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.ResultSet;

public class Bus {
    private Connection conn;
    private String busId;
    private String model;
    private Integer year;


    public Bus(Connection conn){
        this.conn = conn;
    }

    /**
     * Creates a Bus object with its identifying properties.
     *
     * @param busId The bus's unique ID
     * @param model The bus model name
     * @param year  The manufacturing year
     */
    public Bus(String busId, String model, int year){
        setBusId(busId);
        setModel(model);
        setYear(year);
    }

    /**
     * Prompts the user to enter details for a new bus (ID, model, and year),
     * constructs a Bus object, and inserts it into the database.
     *
     * @param conn Active database connection
     * @param scnr Scanner used to capture user input from the console
     */
     public static void addBus(Connection conn, Scanner scnr){
        System.out.println("Enter the bus ID of the bus you would like to add: ");
        String busId = scnr.nextLine();
        System.out.println("Enter the model of the bus you would like to add: ");
        String model = scnr.nextLine();
        System.out.println("Enter the year(YYYY) of the bus you would like to add: ");
        int year = scnr.nextInt();
        scnr.nextLine();

        Bus bus = new Bus(busId, model, year);
        bus.setDBConnection(conn);
        bus.addBus(bus);
    }   

    /**
     * Inserts a new bus record into the database using the provided Bus object.
     *
     * @param bus The bus object containing ID, model, and year to be inserted.
     */
    private void addBus(Bus bus){
        String newBusId = bus.getBusId();
        String newBusModel = bus.getModel();
        int newBusYear = bus.getYear();
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO bus(bus_id, model, year) VALUES (?, ?, ?)");
            ps.setString(1, newBusId);
            ps.setString(2, newBusModel);
            ps.setInt(3, newBusYear);
            ps.execute();

            System.out.println("Bus successfully added!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prompts the user for a bus ID and deletes the corresponding record from the database.
     *
     * @param conn Active database connection
     * @param scnr Scanner used to capture user input
     */
    public static void deleteBus(Connection conn, Scanner scnr){
        System.out.println("Enter the bus ID for the bus you would like to delete:");
        String busId = scnr.nextLine();

        deleteBus(conn, busId);
    }

    /**
     * Deletes a bus record from the database using the specified bus ID.
     *
     * @param conn Active database connection
     * @param busID The unique identifier of the bus to delete
     */
    private static void deleteBus(Connection conn, String busID){
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM bus WHERE bus_id =?");
            ps.setString(1,busID);
            ps.execute();
            System.out.println("Successfully removed " + busID + " from bus table.");
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public String getBusId(){return this.busId; }
    public void setBusId(String newBusId){this.busId = newBusId;}

    public String getModel(){return this.model; }
    public void setModel(String newModel){this.model = newModel;}

    public int getYear(){return this.year; }
    public void setYear(int newYear){this.year = newYear;}

    /**
     * Sets the active database connection for this bus object.
     *
     * @param conn The database connection to assign
     */
    public void setDBConnection(Connection conn){
        this.conn = conn;
    }

    /**
     * Displays all buses from the database in a formatted table.
     * Each row includes bus ID, model, and manufacturing year.
     */
    public void dispBuses(){
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM bus");
            ResultSet rs = ps.executeQuery();
            System.out.printf("\n%-12s %-25s %-15s\n",
            "Bus ID", "Model", "Year");
            System.out.println("-------------------------------------------------------------");
            while(rs.next()){
                String busID = rs.getString("bus_id");
                String busModel = rs.getString("model");
                int busYear = rs.getInt("year");

                System.out.printf("%-12s %-25s %-15s\n",
                busID, busModel, busYear);

            }


        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    
}
