package com.pomonatransit;

import java.util.Scanner;

public interface ActualTripStopInfoDAO {

    /**
     * Prompts the user to enter details for a new actual trip stop info record
     * and inserts the data into the database.
     *
     * @param scnr Scanner used to collect user input from the console
     */
    void insertActualTripStopInfo(Scanner scnr);

    /**
     * Displays all records from the actual_trip_stop_info table in a formatted table.
     * Each row includes trip number, date, scheduled and actual times, stop number, 
     * and passenger counts.
     */
    void dispActualTripStopInfo();
}
