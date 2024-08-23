package Homework5;

public class SameFolderNameException extends Exception {
    /**
     * Default constructor of the NonexistentTreeException class.
     */
    public SameFolderNameException() {
        System.out.println("A folder with the same name already exists.\n");
    }

    /**
     * Overloaded constructor that prints the custom message.
     * @param s
     */
    public SameFolderNameException(String s) {
        System.out.println(s);
    }
}
