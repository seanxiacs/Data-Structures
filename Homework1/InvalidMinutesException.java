package Homework1;

public class InvalidMinutesException extends Exception {
    public InvalidMinutesException() {
        System.out.println("The minutes for the song is invalid.");
    }

    public InvalidMinutesException(String s) {
        System.out.println(s);
    }
}
