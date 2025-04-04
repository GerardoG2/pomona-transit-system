package com.pomonatransit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.ResultSet;

public class TripStopInfoDAOImpl implements TripStopInfoDAO{

    Connection conn;

    public TripStopInfoDAOImpl(Connection conn){
        this.conn = conn;
    }

    @Override
    public void dispTripStops(Scanner scnr){
        System.out.println("Enter a trip number for the trip stop details: ");
        int tripNumber = scnr.nextInt();
        scnr.nextLine();

        dispTripStops(tripNumber);
    }
    
    private void dispTripStops(int tripNumber){
        try{
            PreparedStatement ps = conn.prepareStatement(
                "SELECT trip_number, t.stop_number, stop_address, sequence_number, driving_time FROM trip_stop_info t "+ 
                "INNER JOIN stop s ON t.stop_number = s.stop_number WHERE trip_number = ?");
            ps.setInt(1, tripNumber);
            
            ResultSet rs = ps.executeQuery();
            System.out.printf("\n%-8s %-12s %-12s %-12s\n",
            "Trip #", "Stop #", "Sequence #", "Driving Time");
            System.out.println("-------------------------------------------------------------");
            while(rs.next()){

                int stopNumber = rs.getInt("stop_number");
                int sequenceNumber = rs.getInt("sequence_number");
                String drivingTime = rs.getString("driving_time");

                System.out.printf("%-8d %-12d %-12d %-12s\n",
                tripNumber, stopNumber, sequenceNumber, drivingTime);

            }
            

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
}
