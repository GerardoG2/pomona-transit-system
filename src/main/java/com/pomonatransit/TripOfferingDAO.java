package com.pomonatransit;

import java.util.Scanner;

public interface TripOfferingDAO{
    void dispTripOffering(Scanner scnr);
    void dispTripOfferingSchedule();
    void deleteTripOffering(Scanner scnr);
    void addTripOffering(Scanner scnr);
    void updateDriver(Scanner scnr);
    void updateBus(Scanner scnr);
}