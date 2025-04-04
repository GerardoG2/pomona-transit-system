package com.pomonatransit;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
    Scanner scnr = new Scanner(System.in);

    System.out.print("Enter PostgreSQL username: ");
    String user = scnr.nextLine();

    System.out.print("Enter PostgreSQL password: ");
    String password = scnr.nextLine(); 

    String jdbcUrl = "jdbc:postgresql://localhost:5432/pomona_transit";
    Menu.menu(jdbcUrl, user, password);
    }
}

