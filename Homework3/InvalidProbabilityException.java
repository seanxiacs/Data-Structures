package Homework3;

public class InvalidProbabilityException extends Exception {
    public InvalidProbabilityException() {
        System.out.println("The probability is invalid.\n");
    }

    public InvalidProbabilityException(String s) {
        System.out.println(s);
    }
}
