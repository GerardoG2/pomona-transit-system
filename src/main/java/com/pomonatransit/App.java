package com.pomonatransit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/pomona_transit";
        String user = "postgres";
        String password = "1234"; 

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("✅ Connected to the database!");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM trip");

            while (rs.next()) {
                System.out.println("Trip #: " + rs.getInt("trip_number"));
            }

        } catch (SQLException e) {
            System.out.println("❌ Connection failed.");
            e.printStackTrace();
        }
    }
}

