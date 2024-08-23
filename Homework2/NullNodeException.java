package Homework2;

public class NullNodeException extends Exception {
    public NullNodeException() {
        System.out.println("The node you are trying to perform the action on is null.");
    }

    public NullNodeException(String s) {
        System.out.println(s);
    }
}
