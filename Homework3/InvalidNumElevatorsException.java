package Homework3;

public class InvalidNumElevatorsException extends Exception {
    public InvalidNumElevatorsException() {
        System.out.println("The number of elevators inputted is invalid.\n");
    }

    public InvalidNumElevatorsException(String s) {
        System.out.println(s);
    }
}
