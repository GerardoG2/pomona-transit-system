package com.pomonatransit;

import java.util.Scanner;

public interface TripOfferingDAO{
    /**
     * Prompts the user to enter a start and destination location,
     * then displays matching trip offerings between those locations.
     *
     * @param scnr Scanner used to capture user input
     */
    void dispTripOffering(Scanner scnr);

    /**
     * Displays the full trip offering schedule, including trip number,
     * date, start and arrival times, driver ID, and bus ID.
     */
    void dispTripOfferingSchedule();

    /**
     * Prompts the user to enter trip number, date, and scheduled start time
     * for a trip offering to delete from the database.
     *
     * @param scnr Scanner used to capture user input
     */
    void deleteTripOffering(Scanner scnr);

    /**
     * Prompts the user for details to create a new trip offering and inserts it into the database.
     *
     * @param scnr Scanner used to collect user input
     */
    void addTripOffering(Scanner scnr);

    /**
     * Prompts the user for trip identifiers and a new driver ID, 
     * then updates the assigned driver for the specified trip offering.
     *
     * @param scnr Scanner used to collect user input
     */
    void updateDriver(Scanner scnr);

    /**
     * Prompts the user for trip identifiers and a new bus ID,
     * then updates the assigned bus for the specified trip offering.
     *
     * @param scnr Scanner used to collect user input
     */
    void updateBus(Scanner scnr);


}