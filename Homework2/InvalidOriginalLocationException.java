package Homework2;

public class InvalidOriginalLocationException extends Exception {
    public InvalidOriginalLocationException() {
        System.out.println("The input is not an accepted original location.");
    }

    public InvalidOriginalLocationException(String s) {
        System.out.println(s);
    }
}
