package Homework1;

public class InvalidSecondsException extends Exception {
    public InvalidSecondsException() {
        System.out.println("The seconds for the song is invalid.");
    }

    public InvalidSecondsException(String s) {
        System.out.println(s);
    }
}
