package Homework2;

public class InvalidRfidTagNumberException extends Exception {
    public InvalidRfidTagNumberException() {
        System.out.println("The input is not an accepted RFID.");
    }

    public InvalidRfidTagNumberException(String s) {
        System.out.println(s);
    }
}
