package Homework5;

public class UndeletableFolderException extends Exception {
    /**
     * Default constructor of the NonexistentTreeException class.
     */
    public UndeletableFolderException() {
        System.out.println("The folder you are trying to delete is undeletable.\n");
    }

    /**
     * Overloaded constructor that prints the custom message.
     * @param s
     */
    public UndeletableFolderException(String s) {
        System.out.println(s);
    }
}
