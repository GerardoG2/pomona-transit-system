package com.pomonatransit;

import java.util.Scanner;

public interface TripStopInfoDAO {

    /**
     * Prompts the user to enter a trip number and displays the corresponding
     * sequence of stops along with stop address, sequence number, and driving time.
     *
     * @param scnr Scanner used to collect user input
     */
    void dispTripStops(Scanner scnr);

    
}
