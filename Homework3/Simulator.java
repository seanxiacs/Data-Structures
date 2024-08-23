package Homework3;

/**
 * Simulator is the class that holds the method that will actually run the simulation.
 * @author Sean Xia, SBU ID 113181409, R30
 */

public class Simulator {
    /**
     * This method will simulate the elevators picking up and dropping people off after given 4 important parameters.
     * @param newProb
     * @param newNumFloors
     * @param newNumElevators
     * @param newLengthSimulation
     * @throws InvalidProbabilityException
     * @throws InvalidNumFloorsException
     * @throws InvalidNumElevatorsException
     */
    public static void Simulate(double newProb, int newNumFloors, int newNumElevators, int newLengthSimulation) throws InvalidProbabilityException, InvalidNumFloorsException, InvalidNumElevatorsException {
        if(newProb < 0 || newProb > 1) {
            throw new InvalidProbabilityException("This probability does not lie in the range of 0 and 1 inclusive.\n");
        }
        if(newNumFloors <= 1) {
            throw new InvalidNumFloorsException("The number of floors cannot be less than or equal to 1.\n");
        }
        if(newNumElevators <= 0) {
            throw new InvalidNumElevatorsException("The number of elevators cannot be less than or equal to 0.\n");
        }

        int timeStep = 1;
        RequestQueue queue = new RequestQueue();
        BooleanSource chance = new BooleanSource(newProb);
        Elevator[] elevators = new Elevator[newNumElevators];

        for(int i = 0; i < elevators.length; i++) {
            elevators[i] = new Elevator();
        }

        int totalWaitTime = 0;
        int totalRequests = 0;

        while(timeStep <= newLengthSimulation) {
            //There will be one queue of requests for the simulation. The queue is a standard FIFO queue, meaning that
            // the first request placed is the first to be granted.
            //Each elevator can only handle one passenger at a time.
            //A single time unit represents the amount of time it takes for the elevator to move up or down one floor.
            //At the beginning of each time unit, there is a random chance that a new request will be placed by a
            // passenger. There may be at most one new request placed per time unit. Requests have a randomly generated
            // source floor (where the passenger is) and destination floor (where the passenger wishes to go).
            if(chance.requestArrived()) {
                Request newReq = new Request(newNumFloors);
                newReq.setTimeEntered(timeStep);
                queue.enqueue(newReq);
            }

            //After checking for a possible new request, all elevators that are currently idle
            // (i.e., not handling a request) will be given a request to handle, if such a request exists. It does not
            // matter which elevator is assigned to handle a request.
            int checkElevator = 0;
            while((queue.size() != 0) && (checkElevator < newNumElevators)) {
                if(elevators[checkElevator].getElevatorState() == Elevator.IDLE) {
                    elevators[checkElevator].setCurrentRequest(queue.dequeue());
                    elevators[checkElevator].setElevatorState(Elevator.TO_SOURCE);

                }
                checkElevator++;
            }

            //At the end of each time unit, all elevators that are processing requests move one floor closer to
            // completing the request. If the elevator was moving to pick up a passenger, the elevator will move one
            // floor closer to the source floor. If the elevator was moving to drop off the passenger, it will move one
            // floor closer to the destination floor.


            //If the source floor is reached, the elevator will begin heading towards the destination floor on the next
            // time unit.
            //If the destination floor is reached, the elevator will then be marked as idle (on the next time step, it
            // can handle a new request).
            //If an elevator just begins handling a request on the current time unit and if it was already on the source
            // floor, it may begin moving towards the destination on the current time step.
            //If an elevator is on the source floor and if the source floor is also the destination floor, the elevator
            // may be set to idle (it has reached its destination and can handle a new request on the next time unit).
            for(int i = 0; i < elevators.length; i++) {
                Elevator currentElevator = elevators[i];
                if(currentElevator.getElevatorState() == Elevator.TO_SOURCE) { //Make sure state is going to source.
                    if(currentElevator.getCurrentFloor() == currentElevator.getCurrentRequest().getSourceFloor()) { //If elevator is at source.
                        if(currentElevator.getCurrentRequest().getTimeEntered() == timeStep) {
                            //Don't need to add to totalWaitTime because the wait time will be 0.
                            totalRequests++;
                            currentElevator.setElevatorState(Elevator.TO_DESTINATION);

                            //Needs separate condition because it moves towards destination in the same timeStep if the request is at the floor that the elevator is at.
                            if(currentElevator.getCurrentFloor() == currentElevator.getCurrentRequest().getDestinationFloor()) {
                                currentElevator.setElevatorState(Elevator.IDLE);
                                currentElevator.setCurrentRequest(null);
                            }
                            else if(currentElevator.getCurrentFloor() < currentElevator.getCurrentRequest().getDestinationFloor()) {
                                currentElevator.setCurrentFloor(currentElevator.getCurrentFloor() + 1);
                            }
                            else {
                                currentElevator.setCurrentFloor(currentElevator.getCurrentFloor() - 1);
                            }
                        }
                        else {
                            totalWaitTime += timeStep - currentElevator.getCurrentRequest().getTimeEntered();
                            totalRequests++;
                            currentElevator.setElevatorState(Elevator.TO_DESTINATION);
                        }
                    }
                    else if(currentElevator.getCurrentFloor() < currentElevator.getCurrentRequest().getSourceFloor()) { //If elevator is below source.
                        currentElevator.setCurrentFloor(currentElevator.getCurrentFloor() + 1);
                    }
                    else { //If elevator is above source.
                        currentElevator.setCurrentFloor(currentElevator.getCurrentFloor() - 1);
                    }
                }
                else if(currentElevator.getElevatorState() == Elevator.TO_DESTINATION) { //Make sure state is going to destination.
                    if(currentElevator.getCurrentFloor() == currentElevator.getCurrentRequest().getDestinationFloor()) { //If elevator is at destination.
                        currentElevator.setElevatorState(Elevator.IDLE);
                        currentElevator.setCurrentRequest(null);
                    }
                    else if(currentElevator.getCurrentFloor() < currentElevator.getCurrentRequest().getDestinationFloor()) { //If elevator is below destination.
                        currentElevator.setCurrentFloor(currentElevator.getCurrentFloor() + 1);
                    }
                    else { //If elevator is above destination.
                        currentElevator.setCurrentFloor(currentElevator.getCurrentFloor() - 1);
                    }
                }
                else {
                    //System.out.println("This elevator is idle!");
                }
            }
            timeStep++;
        }

        //Your task is to calculate the average waiting time for requests.
        //Wait time is defined as the number of time units that pass from when a request is placed on the queue
        // until the elevator picks up the passenger (i.e., until the elevator arrives at the source floor).
        //You will add the amount of waiting time for a request to the waiting sum and increment the request count
        // as each request has its elevator reach its source floor.
        //In your calculations, you must ignore all requests that have not had an assigned elevator reach its source
        // floor.

        //Note: When an elevator has reached the source floor of the request it is handling, the waiting time
        // officially ends. The time it takes the elevator to reach the destination is not counted.

        System.out.println("Total Wait Time: " + totalWaitTime);
        System.out.println("Total Requests: " + totalRequests);
        double avgWaitTime = (double) totalWaitTime/totalRequests;
        if(avgWaitTime != 0) {
            System.out.println("Average Wait Time: " + String.format("%.2f", (double) totalWaitTime/totalRequests));
        }
        else {
            System.out.println("Average Wait Time: " + String.format("%.2f", (double) 0));
            System.out.println("The average wait time can be 0 if no elevators were able to reach the passengers (at the sources of the request) or the elevators were at the same floor as the requests.");
        }
    }
}
