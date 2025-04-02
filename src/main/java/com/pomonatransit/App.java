package com.pomonatransit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/pomona_transit";
        String user = "postgres";
        String password = "1234"; 

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("✅ Connected to the database!");

            TripOfferingDAOImpl tripOffer = new TripOfferingDAOImpl(conn);
            tripOffer.disp_trip_offering_schedule(); 
            Scanner scnr = new Scanner(System.in);
            System.out.println("Enter the trip number to delete");
            System.out.println("Enter the date to delete");
            System.out.println("Enter the time to delete");
            int tripNumToDelete = scnr.nextInt();
            String dateToDelete = scnr.next();
            String timeToDelete = scnr.next();
            


            tripOffer.deleteTripOffering(tripNumToDelete, dateToDelete, timeToDelete);




        } catch (SQLException e) {
            System.out.println("❌ Connection failed.");
            e.printStackTrace();
        }
    }


}

