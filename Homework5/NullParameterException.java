package Homework5;

public class NullParameterException extends Exception {
    /**
     * Default constructor of the NonexistentTreeException class.
     */
    public NullParameterException() {
        System.out.println("The parameter entered is null\n");
    }

    /**
     * Overloaded constructor that prints the custom message.
     * @param s
     */
    public NullParameterException(String s) {
        System.out.println(s);
    }
}
