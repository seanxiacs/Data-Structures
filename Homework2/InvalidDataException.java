package Homework2;

public class InvalidDataException extends Exception {
    public InvalidDataException() {
        System.out.println("The data being inputted is invalid.");
    }

    public InvalidDataException(String s) {
        System.out.println(s);
    }
}
