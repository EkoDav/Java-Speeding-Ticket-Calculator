/*
 * TicketAnalysis.java program will provide a variety of analysis calculations
 * for speeding tickets based on data read from a ticket data file. The program
 * is currently set to process a maximum of 200 tickets. 
 */

import java.util.Scanner;

/**
 * The main method will display the program description to the user, prompt for
 * an input file, and call upon the other methods to analyze and store the data.
 * 
 * @author Erik Davis
 * @version 1.0 Java Assignment 8
 */
public class TicketAnalysis {
    
    public static void main(String[] args) {
        final int MAX_TICKETS = 200;
        char answer;
        int ticketsRead = 0;
        String inFileName;
        
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("This program will run analysis on weekly speeding"
                + " ticket data files.");
        System.out.println();
        
        do {
            TicketArrayImpl newList = new TicketArrayImpl();
            
            while (ticketsRead == 0) {
                System.out.print("Please enter input filename: ");
                inFileName = keyboard.next();
                ticketsRead = newList.readTickets(inFileName);
            }
            
            System.out.println("Calculating fines...");
            
            double [] ticketFines = new double[MAX_TICKETS];
            newList.calcFines(ticketFines);
            System.out.println("Done!");
            System.out.println();
            newList.produceFinesReport(ticketFines);
            
            displayTicketSummary(ticketFines, ticketsRead);
            
            System.out.print("Run program again with another file (y/n)? ");
            answer = keyboard.next().charAt(0);
            System.out.println();
        } while (answer == 'y' || answer == 'Y');
    }
    
    /**
     * minFine will use the Fines Array to find the lowest fine among all the 
     * tickets in the array.
     * 
     * @param ticketFines - The array containing data on ticket fines. 
     * @param ticketCount - Total number of tickets processed. 
     * 
     * @return ticketFines[lowIndex] - The value in the index with lowest fine.
     */
    static double minFine(double [] ticketFines, int ticketCount) {
        int lowIndex = 0;
        
        for (int count = 1; count < ticketCount; count++) {
            if (ticketFines[count] < ticketFines[lowIndex])
                lowIndex = count;
        }
        
        return ticketFines[lowIndex];
    }
    
    /**
     * averageFine will use the Fines Array to find the average fine using all 
     * the tickets in the array.
     * 
     * @param ticketFines - The array containing data on ticket fines. 
     * @param ticketCount - Total number of tickets processed.
     * 
     * @return average - The average fine amount among all tickets.
     */
    static double averageFine(double [] ticketFines, int ticketCount) {
        double sum = 0;
        double average;
        
        for (int count = 0; count < ticketCount; count++) {
            sum = sum + ticketFines[count];
        }
        average = sum / ticketCount;
        
        return average;
    }
        
    /**
     * maxFine will use the Fines Array to find the highest fine among all the 
     * tickets in the array.
     * 
     * @param ticketFines - The array containing data on ticket fines. 
     * @param ticketCount - Total number of tickets processed.
     * 
     * @return highestFine - The amount of the highest fine among the tickets.
     */
    static double maxFine(double [] ticketFines, int ticketCount) {
        double highestFine = 0;
        
        for (int count = 0; count < ticketCount; count++) {
            if (ticketFines[count] > highestFine)
                highestFine = ticketFines[count];
        }
        
        return highestFine;
    } 
    
    /**
     * displayTicketSummary with call on the calculation methods for analyzing
     * the tickets and display the results in a specified format. 
     * 
     * @param ticketFines - The array containing data on ticket fines. 
     * @param ticketCount - Total number of tickets processed.
     */
    static void displayTicketSummary(double [] ticketFines, int ticketCount) {
        double lowFine = minFine(ticketFines, ticketCount);
        double avFine = averageFine(ticketFines, ticketCount);
        double highFine = maxFine(ticketFines, ticketCount);
        String disLowFine = "Lowest ticket fine";
        String disAvFine = "Average ticket fine";
        String disHighFine = "Highest ticket fine";
        
        System.out.println("Week's Ticket Analysis for " + ticketCount 
                + " tickets issued:");
        System.out.printf("    %-20s%15.2f%n", disLowFine, lowFine);
        System.out.printf("    %-20s%15.2f%n", disAvFine, avFine);
        System.out.printf("    %-20s%15.2f%n", disHighFine, highFine);
        System.out.println();
    }
}