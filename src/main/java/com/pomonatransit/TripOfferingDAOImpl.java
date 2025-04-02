package com.pomonatransit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.pomonatransit.TripOffering;

public class TripOfferingDAOImpl implements TripOfferingDAO{
    private Connection conn;


    public TripOfferingDAOImpl(Connection conn){
        this.conn = conn;
    }

    public void disp_trip_offering_schedule(){
        try {
            PreparedStatement ps = conn.prepareStatement("select * from trip_offering");
            ResultSet rs = ps.executeQuery();

            System.out.printf("%-8s %-12s %-12s %-12s %-10s %-8s\n",
            "Trip #", "Date", "Start Time", "Arrival Time", "Driver", "Bus");
            System.out.println("-------------------------------------------------------------");
            while(rs.next()){
                int tripNumber = rs.getInt("trip_number");
                String date = rs.getString("date");
                String scheduledStartTime = rs.getString("scheduled_start_time");
                String scheduledArrivalTime = rs.getString("scheduled_arrival_time");
                String driverId = rs.getString("driver_id");
                String busId = rs.getString("bus_id");

                System.out.printf("%-8d %-12s %-12s %-12s %-10s %-8s\n",
                tripNumber, date, scheduledStartTime, scheduledArrivalTime, driverId, busId);



            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        }
    @Override
    public void deleteTripOffering(int tripNumber, LocalDate date, LocalTime scheduledStartTime){

    }

    @Override
    public void addTripOffering (TripOffering tripOffering){

    }

    @Override
    public void updateDriver(int tripNumber, LocalDate date, LocalTime scheduledStartTime, String newDrivername, String newDriverID){

    }

    @Override
    public void updateBus(int tripNumber, LocalDate date, LocalTime scheduledStartTime, String newBusID){

    }

    @Override
    public List<TripOffering> getTripSchedule (String startLocation, String destination, LocalDate date){
        List<TripOffering> schedule = new ArrayList<>();
        

        return schedule;
    }

}


