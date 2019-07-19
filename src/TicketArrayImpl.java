/*
 * TicketArratImpl.java will be used to create an array to store ticket 
 * instances, as well to perform various other tasks to read and compute
 * data from a ticket file. 
 */

import java.util.Scanner;
import java.io.*;

/**
 * TicketArrayImpl will implement an array to store information on all tickets
 * entered into the program. Also includes methods to conduct various calculations.
 * 
 * @author Erik Davis
 * @version 1.0 Java Assignment 8
 */
public class TicketArrayImpl {
    final int MAX_TICKETS = 200;
    
    //Data Fields
    private SpeedingTicket [] ticketList;
    private int ticketCount;
    
    /**
     * Constructor for Ticket Array without parameters
     */
    public TicketArrayImpl() {
    ticketList = new SpeedingTicket[MAX_TICKETS];
    ticketCount = 0;
    }
    
    /**
     * addTicket will check to make sure the array is not full. If so, then it
     * will add the ticket to the array. 
     * 
     * @param oneTicket - An instance of a ticket. 
     */
    public void addTicket(SpeedingTicket oneTicket) {
        if (ticketCount >= ticketList.length) {
            throw new ArrayIndexOutOfBoundsException ("**Ticket array is FULL."
                    + " The ticket for plate <" + oneTicket.getLicensePlate()
                    + "> could not be added.");
        } else {
            ticketList[ticketCount] = oneTicket;
            ticketCount++;
        }
    }
    
    /**
     * readTickets will open the data file, read the data, and call other methods
     * to create an object of it and store it. Will check to make sure the file
     * can be opened and read. 
     * 
     * @param fileName - The input filename retrieved from user.
     * @return totalTickets - Total number of tickets read.
     */
    public int readTickets(String fileName) {
        int totalTickets = 0;
        String licensePlate;
        int speedLimit;
        int clockedSpeed;
        boolean full = false;
        
        try {
            File inFile = new File(fileName);
            Scanner fin = new Scanner(inFile);
            
            System.out.println("Reading data file...");
            System.out.println();
            
            while (fin.hasNext() && full == false) {
                licensePlate = fin.next();
                speedLimit = fin.nextInt();
                clockedSpeed = fin.nextInt();
                
                SpeedingTicket oneTicket = new SpeedingTicket(licensePlate,
                    speedLimit, clockedSpeed);
                
                try {
                    this.addTicket(oneTicket);
                    totalTickets++;
                } catch (ArrayIndexOutOfBoundsException aibe) {
                    System.out.println(aibe.getMessage());
                    System.out.println("No more data will be read from the file.");
                    System.out.println();
                    full = true;
                }
            }
            fin.close();
            
            System.out.println("Total number of tickets stored: " + totalTickets);
            System.out.println();
            
        } catch (FileNotFoundException fnfe) {
            System.out.println("**The data file could not be found.");
        }
        return totalTickets;
    }
    
    /**
     * calcFines will take the data in the ticket array and calculate the fines
     * appropriate for that ticket based on client provided values set as
     * constants. 
     * 
     * @param ticketFines - A blank array to store the fines of tickets. 
     */
    public void calcFines(double [] ticketFines) {
        final double COURT_FEE = 45;
        final int LOW_MPH = 10;
        final int HIGH_MPH = 20;
        final double LOW_FINE = 4.25;
        final double MED_FINE = 6;
        final double HIGH_FINE = 8.10;
        int speedLimit;
        int clockedSpeed;
        int speedDifference;
        double ticketFine;
        
        for (int count = 0; count < ticketCount; count++) {
            speedLimit = ticketList[count].getSpeedLimit();
            clockedSpeed = ticketList[count].getClockedSpeed();
            speedDifference = clockedSpeed - speedLimit;
            if (speedDifference <= LOW_MPH) {
                ticketFine = speedDifference * LOW_FINE + COURT_FEE;
            } else if (speedDifference <= HIGH_MPH) {
                ticketFine = speedDifference * MED_FINE + COURT_FEE;
            } else {
                ticketFine = speedDifference * HIGH_FINE + COURT_FEE;
            }
            ticketFines[count] = ticketFine;
        }
    }
    
    /**
     * produceFinesReport will prompt the user for the file to print the fines
     * report, check that the file can be opened, and then print the analysis
     * report for all tickets in specified format. 
     * 
     * @param ticketFines - The array containing ticket fine data.
     */
    public void produceFinesReport(double [] ticketFines) {
        String outFileName;
        String licensePlate;
        String disTotal = "Total";
        double fine;
        double totalFines = 0;
        
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Please type a file name for the output Fines Report: ");
        outFileName = keyboard.next();
        System.out.println();
        
        try {
            File outFile = new File(outFileName);
            PrintWriter fout = new PrintWriter(outFile);
            
            for (int count = 0; count < ticketCount; count++) {
                licensePlate = ticketList[count].getLicensePlate();
                fine = ticketFines[count];
                fout.printf("%-6s%9.2f%n", licensePlate, fine);
                totalFines += fine;
            }
            
            fout.printf("%-6s%9.2f", disTotal, totalFines);
            
            fout.close();
        
        } catch (IOException ioex) {
            System.out.println("The file " + outFileName + " could not be"
                    + " opened. A fines report will not be generated.");
        }
    }
}