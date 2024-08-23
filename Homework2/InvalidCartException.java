package Homework2;

public class InvalidCartException extends Exception {
    public InvalidCartException() {
        System.out.println("The input is not an accepted cart.");
    }

    public InvalidCartException(String s) {
        System.out.println(s);
    }
}
