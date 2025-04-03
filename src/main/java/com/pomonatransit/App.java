package com.pomonatransit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;


public class App {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/pomona_transit";
        String user = "postgres";
        String password = "1234"; 

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("✅ Connected to the database!");

            TripOfferingDAOImpl tripOfferDAO = new TripOfferingDAOImpl(conn);
            // tripOfferDAO.disp_trip_offering_schedule(); 
            // Scanner scnr = new Scanner(System.in);
            // System.out.println("Enter the trip number to delete");
            // System.out.println("Enter the date to delete");
            // System.out.println("Enter the time to delete");
            // int tripNumToDelete = scnr.nextInt();
            // String dateToDelete = scnr.next();
            // String timeToDelete = scnr.next();

            // int tripNumber = 2005;
            // String date = "2025-04-10";
            // String scheduledStartTime = "10:56:00";
            // String scheduledArrivalTime = "11:30:00";
            // String driverId = "Driver 3";
            // String busId = "BUS001";

            // tripOfferDAO.updateBus(tripNumber, date, scheduledStartTime, busId);
            // // TripOffering TripOfferingAddition = new TripOffering(tripNumber, date, scheduledStartTime, scheduledArrivalTime, driverId, busId);
            // // tripOfferDAO.addTripOffering(TripOfferingAddition);

            // tripOfferDAO.disp_trip_offering_schedule();

            // TripStopInfoDAO tripStopInfoDAO = new TripStopInfoDAOImpl(conn);
            // tripStopInfoDAO.dispTripStops(2010);
            // String driverid = "Driver 03";
            // String startDate = "2025-04-04";
            // String endDate = "2025-04-30";
            // tripOfferDAO.dispDriverSchedule(driverid, startDate, endDate);

            

            


            // tripOffer.deleteTripOffering(tripNumToDelete, dateToDelete, timeToDelete);


            // Driver driver = new Driver(conn);
            // driver.setDBConnection(conn);

            // driver.deleteDriver("(909)222-3333");
            // driver.deleteDriver("(909)222-3334");

            // driver.dispDrivers();
            // driver.addNewDriver(driver);
            // driver.dispDrivers();

            Bus bus = new Bus("Bus 999", "Model Z", 2025);
            bus.setDBConnection(conn);

            bus.dispBuses();

            bus.addNewBus(bus);

            bus.dispBuses();

            bus.deleteBus("Bus 999");

            bus.dispBuses();



        } catch (SQLException e) {
            System.out.println("❌ Connection failed.");
            e.printStackTrace();
        }
    }


}

