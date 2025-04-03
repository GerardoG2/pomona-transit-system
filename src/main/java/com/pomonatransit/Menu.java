package com.pomonatransit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    public static void menu(){
        String url = "jdbc:postgresql://localhost:5432/pomona_transit";
        String user = "postgres";
        String password = "1234"; 

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Scanner scnr = new Scanner(System.in);) {

            System.out.println("Welcome to Pomona Transit.");
            
            String userInput = "";
            while (!userInput.equals("0")){
                printMenuOptions();
                userInput = scnr.nextLine();
                menuSelection(conn, userInput);
            }
            System.out.println("Session Ended.");




        } catch (SQLException e) {
            System.out.println("❌ Connection failed.");
            e.printStackTrace();
        }

        



    }

    /** Only prints menu options.
     * Use menu() to interact with menu.
     * */
    public static void printMenuOptions(){
        System.out.println("\nPlease select an option.");
        System.out.println("-------------------------------------------------------------");
        System.out.println("1. Display trip offering schedule.");
        System.out.println("2. Delete a trip offering.");
        System.out.println("3. Add trip offering(s).");
        System.out.println("4. Change driver assigned to trip offering.");
        System.out.println("5. Change bus assigned to trip offering.");
        System.out.println("6. Display the stops for a trip.");
        System.out.println("7. Display a driver's schedule.");
        System.out.println("8. Add a new driver to list of drivers.");
        System.out.println("9. Add a bus to list of buses.");
        System.out.println("10. Remove bus from bus list.");
        System.out.println("11. Insert 'Actual Trip Stop Info'.");
        System.out.println("0. EXIT\n");
    }

    /** Helper method for menu().
     * Executes database access method based off user input.
     */
    public static void menuSelection(Connection conn,String selection){
        TripOfferingDAO tripOfferingDAO;
        switch(selection){
            case "1":
                tripOfferingDAO = new TripOfferingDAOImpl(conn);
                tripOfferingDAO.dispTripOfferingSchedule();
                break;
            case "2":
                tripOfferingDAO = new TripOfferingDAOImpl(conn);
                tripOfferingDAO.deleteTripOffering();
                break;
            case "3":
                tripOfferingDAO = new TripOfferingDAOImpl(conn);
                tripOfferingDAO.addTripOffering();
                break;
            case "4":
                
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "10":
            case "11":
            case "0":
        }
    }
    
}
