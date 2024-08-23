package Homework3;

/**
 * Request holds information about the elevator request. This includes the source of the request, where the requester intends to go, and the time the request was made.
 * @author Sean Xia, SBU ID 113181409, R30
 */

public class Request {
    private int sourceFloor;
    private int destinationFloor;
    private int timeEntered;

    /**
     * Default constructor of the Request class.
     */
    public Request() {

    }

    /**
     * Overloaded constructor that sets all the information about the request, and makes sure that the preconditions are valid.
     * @param newNumBuildingFloors
     */
    public Request(int newNumBuildingFloors) {
        this.sourceFloor = (int) (Math.random() * newNumBuildingFloors + 1);
        //Do we need the numBuildingFloors variable? Change if not necessary later.
        this.destinationFloor = (int) (Math.random() * newNumBuildingFloors + 1);
        //What happens if the floors are the same? Remove the person?
    }

    /**
     * This method sets the source floor.
     * @param newSourceFloor
     */
    public void setSourceFloor(int newSourceFloor) {
        this.sourceFloor = newSourceFloor;
    }

    /**
     * This method sets the destination floor.
     * @param newDestinationFloor
     */
    public void setDestinationFloor(int newDestinationFloor) {
        this.destinationFloor = newDestinationFloor;
    }

    /**
     * This method sets the time of the request.
     * @param newTimeEntered
     */
    public void setTimeEntered(int newTimeEntered) {
        this.timeEntered = newTimeEntered;
    } //Important later

    /**
     * This method gets the source floor.
     * @return sourceFloor
     */
    public int getSourceFloor() {
        return sourceFloor;
    }

    /**
     * This method gets the destination floor.
     * @return destinationFloor
     */
    public int getDestinationFloor() {
        return destinationFloor;
    }

    /**
     * This method gets the time of the request.
     * @return timeEntered
     */
    public int getTimeEntered() {
        return timeEntered;
    }
}
