package Homework3;

/**
 * Analyzer holds the main method and runs the simulation after getting required inputs.
 * @author Sean Xia, SBU ID 113181409, R30
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class Analyzer {
    private static final Scanner input = new Scanner(System.in);
    public static boolean debug = false;

    /**
     * This method runs the simulation and ensures that the simulation does not cause error messages to appear.
     * @param args
     * @throws InvalidProbabilityException
     * @throws InvalidNumFloorsException
     * @throws InvalidNumElevatorsException
     */
    public static void main(String[] args) throws InvalidProbabilityException, InvalidNumFloorsException, InvalidNumElevatorsException {
        System.out.println("Welcome to the Elevator Simulator!\n");

        try {
            System.out.print("Please enter the probability of arrival for Requests: ");
            double prob = input.nextDouble();
            System.out.print("Please enter the number of floors: ");
            int numFloors = input.nextInt();
            System.out.print("Please enter the number of elevators: ");
            int numElevators = input.nextInt();
            System.out.print("Please enter the length of the simulation (in time units): ");
            int lengthSimulation = input.nextInt();
            System.out.println();

            Simulator.Simulate(prob, numFloors, numElevators, lengthSimulation);

        } //Catch the other errors.
        catch(InvalidProbabilityException e) {
        }
        catch(InvalidNumFloorsException e) {
        }
        catch(InvalidNumElevatorsException e) {
        }
        catch(IllegalArgumentException e) {
            System.out.println("Please enter a position that is within the valid range next time.\n");
        }
        catch(InputMismatchException e) {
            System.out.println("Please enter an argument of the right type.\n");
        }
        catch(NullPointerException e) {
            System.out.println("There seems to be a null-pointer exception occurring.");
        }
        catch(Exception e) {
            System.out.println("There seems to be an error that was not entirely accounted for.\n");
        }

        input.close();
    }
}