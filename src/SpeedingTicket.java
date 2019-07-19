/*
 * SpeedingTicket.java will create instances of tickets using three data 
 * fields. To be used with the Ticket Analysis program. 
 */

/**
 * SpeedingTicket class will set data fields, construct an instance of a ticket
 * and define getters.
 * 
 * @author Erik Davis
 * @version 1.0 Java Assignment 8
 */
public class SpeedingTicket {
    //Data Fields
    private String licensePlate;
    private int speedLimit;
    private int clockedSpeed;
    
    /**
     * Constructor for Speeding Ticket
     * 
     * @param licensePlate - License plate number of vehicle.
     * @param speedLimit - Speed limit of area of violation.
     * @param clockedSpeed - Recorded speed of vehicle.
     */
    public SpeedingTicket (String licensePlate, int speedLimit, 
            int clockedSpeed) {
        this.licensePlate = licensePlate;
        this.speedLimit = speedLimit;
        this.clockedSpeed = clockedSpeed;
    }
    
    //Getters
    public String getLicensePlate() { return licensePlate;}
    public int getSpeedLimit() { return speedLimit;}
    public int getClockedSpeed() { return clockedSpeed;}
}