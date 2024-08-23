package Homework3;

/**
 * Elevator holds important information about the elevator. It has the current floor, the current state, and the current request that it is fulfilling.
 * @author Sean Xia, SBU ID 113181409, R30
 */

public class Elevator {
    private int currentFloor;
    private int elevatorState;

    public static final int IDLE = 0;
    public static final int TO_SOURCE = 1;
    public static final int TO_DESTINATION = 2;

    private Request currentRequest;

    /**
     * Default constructor of the Elevator class. It also sets up the elevator to be doing nothing, idle, and on floor 1.
     */
    public Elevator() {
        this.currentRequest = null;
        this.elevatorState = IDLE;
        this.currentFloor = 1;
    }

    /**
     * This method sets the current floor.
     * @param newCurrentFloor
     */
    public void setCurrentFloor(int newCurrentFloor) {
        this.currentFloor = newCurrentFloor;
    }

    /**
     * This method sets the elevator state.
     * @param newElevatorState
     */
    public void setElevatorState(int newElevatorState) {
        this.elevatorState = newElevatorState;
    }

    /**
     * This method sets the current request.
     * @param newCurrentRequest
     */
    public void setCurrentRequest(Request newCurrentRequest) {
        this.currentRequest = newCurrentRequest;
    }

    /**
     * This method gets the current floor.
     * @return currentFloor
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    /**
     * This method gets the elevator state.
     * @return elevatorState
     */
    public int getElevatorState() {
        return elevatorState;
    }

    /**
     * This method gets the current request.
     * @return currentRequest
     */
    public Request getCurrentRequest() {
        return currentRequest;
    }

}
