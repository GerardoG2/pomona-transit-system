package com.pomonatransit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Driver {
    private Connection conn;
    private String driverId;
    private String driverName;
    private String driverPhone;


    public Driver(Connection conn){
        this.conn = conn;
    }

    public Driver(String driverID, String driverNAME, String driverPHONE){
        setDriverId(driverID);
        setDriverName(driverNAME);
        setDriverPhone(driverPHONE);
    }

    public void addNewDriver(Driver driver){
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

    
}
