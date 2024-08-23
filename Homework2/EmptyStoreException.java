package Homework2;

public class EmptyStoreException extends Exception {
    public EmptyStoreException() {
        System.out.println("The store has no items in it.");
    }

    public EmptyStoreException(String s) {
        System.out.println(s);
    }
}
