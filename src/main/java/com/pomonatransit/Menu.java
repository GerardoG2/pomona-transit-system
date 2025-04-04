package com.pomonatransit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    /**
     * Establishes DB connection and launches CLI.
     * @param jdbcUrl JDBC connection string, e.g. "jdbc:postgresql://localhost:5432/db"
     * @param databaseUserName Username for PostgreSQL connection
     * @param dbPassword Password for PostgreSQL connection
     */
    public static void menu(String jdbcUrl, String databaseUserName, String dbPassword){
        try (Connection conn = DriverManager.getConnection(jdbcUrl, databaseUserName, dbPassword);
             Scanner scnr = new Scanner(System.in);) {

            System.out.println("Welcome to Pomona Transit.");
            
            String userInput = "";
            while (!userInput.equals("0")){
                printMenuOptions();
                userInput = scnr.nextLine();
                menuSelection(conn,scnr, userInput);
            }
            System.out.println("Session Ended.");




        } catch (SQLException e) {
            System.out.println("❌ Connection failed.");
            e.printStackTrace();
        }

        



    }

    /**
     * Displays the interactive menu options for the user interface.
     * This method does not handle input or logic — it only prints the menu.
     * Called by the menu() method before capturing user selection.
     */
    public static void printMenuOptions(){
        System.out.println("\nPlease select an option.");
        System.out.println("-------------------------------------------------------------");
        System.out.println("1. Show trip offering schedule for start location and destination. ");
        System.out.println("2. Show full trip offering schedule. ");
        System.out.println("3. Delete a trip offering.");
        System.out.println("4. Add trip offering(s).");
        System.out.println("5. Change driver assigned to trip offering.");
        System.out.println("6. Change bus assigned to trip offering.");
        System.out.println("7. Display the stops for a trip.");
        System.out.println("8. Display a driver's schedule.");
        System.out.println("9. Add a new driver to list of drivers.");
        System.out.println("10. Add a bus to list of buses.");
        System.out.println("11. Remove bus from bus list.");
        System.out.println("12. Insert 'Actual Trip Stop Info'.");
        System.out.println("0. EXIT\n");
    }

    /**
     * Handles the user's menu selection by invoking the corresponding database operation.
     * This method delegates each menu option to the appropriate DAO method.
     *
     * @param conn Active database connection used by DAOs
     * @param scnr Scanner for capturing any further user input (if needed)
     * @param selection User's menu choice as a string (e.g. "0", "1", "2", ..., "12")
     */
    public static void menuSelection(Connection conn, Scanner scnr, String selection){
        TripOfferingDAO tripOfferingDAO;
        TripStopInfoDAO tripStopInfoDAO;
        ActualTripStopInfoDAO actualTripStopInfoDAO;
        switch(selection){
            case "1":
                tripOfferingDAO = new TripOfferingDAOImpl(conn);
                tripOfferingDAO.dispTripOffering(scnr);
                break;
            case "2":
                tripOfferingDAO = new TripOfferingDAOImpl(conn);
                tripOfferingDAO.dispTripOfferingSchedule();
                break;
            case "3":
                tripOfferingDAO = new TripOfferingDAOImpl(conn);
                tripOfferingDAO.deleteTripOffering(scnr);
                break;
            case "4":
                tripOfferingDAO = new TripOfferingDAOImpl(conn);
                tripOfferingDAO.addTripOffering(scnr);
                break;
            case "5":
                tripOfferingDAO = new TripOfferingDAOImpl(conn);
                tripOfferingDAO.updateDriver(scnr);
                break;
            case "6":
                tripOfferingDAO = new TripOfferingDAOImpl(conn);
                tripOfferingDAO.updateBus(scnr);
                break;
            case "7":
                tripStopInfoDAO = new TripStopInfoDAOImpl(conn);
                tripStopInfoDAO.dispTripStops(scnr);
                break;
            case "8":
                Driver.dispDriverSchedule(conn, scnr);
                break;
            case "9":
                Driver.addDriver(conn, scnr);
                break;
            case "10":
                Bus.addBus(conn, scnr);
                break;
            case "11":
                Bus.deleteBus(conn, scnr);
                break;
            case "12":
                actualTripStopInfoDAO = new ActualTripStopInfoDAOImpl(conn);
                actualTripStopInfoDAO.insertActualTripStopInfo(scnr);
                break;
        }
    }
    
}
