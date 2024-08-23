package Homework2;

public class InvalidCurrentLocationException extends Exception {
    public InvalidCurrentLocationException() {
        System.out.println("The input is not an accepted current location.");
    }

    public InvalidCurrentLocationException(String s) {
        System.out.println(s);
    }
}
