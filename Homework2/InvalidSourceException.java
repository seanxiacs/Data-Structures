package Homework2;

public class InvalidSourceException extends Exception {
    public InvalidSourceException() {
        System.out.println("The input source is already out of the store.");
    }

    public InvalidSourceException(String s) {
        System.out.println(s);
    }
}
