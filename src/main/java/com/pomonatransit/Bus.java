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

    public Bus(String busId, String model, int year){
        setBusId(busId);
        setModel(model);
        setYear(year);
    }


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

    public static void deleteBus(Connection conn, Scanner scnr){
        System.out.println("Enter the bus ID for the bus you would like to delete:");
        String busId = scnr.nextLine();
        
        deleteBus(conn, busId);
    }

    private static void deleteBus(Connection conn, String busID){
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM bus WHERE bus_id =?");
            ps.setString(1,busID);
            ps.execute();
            System.out.println("Successfully removed " + busID + " from bus table.");
        } catch(SQLException e){

        }
    }

    public String getBusId(){return this.busId; }
    public void setBusId(String newBusId){this.busId = newBusId;}

    public String getModel(){return this.model; }
    public void setModel(String newModel){this.model = newModel;}

    public int getYear(){return this.year; }
    public void setYear(int newYear){this.year = newYear;}

    public void setDBConnection(Connection conn){
        this.conn = conn;
    }

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
