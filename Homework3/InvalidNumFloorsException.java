package Homework3;

public class InvalidNumFloorsException extends Exception {
    public InvalidNumFloorsException() {
        System.out.println("The number of floors inputted is invalid.\n");
    }

    public InvalidNumFloorsException(String s) {
        System.out.println(s);
    }
}
