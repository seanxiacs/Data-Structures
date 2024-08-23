package Homework4;

/**
 * IncorrectFileFormatException is thrown when while loading the tree, the program finds an error with the format of the file that it is told to read and stops the program immediately.
 * @author Sean Xia, SBU ID 113181409, sean.xia@stonybrook.edu, HW#4, CSE214, R30, TAs: Charles Clark, Amogh Joshi, Sharfuddin Mohammed, Vinayak Shenoy.
 */

public class IncorrectFileFormatException extends Exception {
    /**
     * Default constructor of the IncorrectFileFormatException class.
     */
    public IncorrectFileFormatException() {
        System.out.println("There was an error in the file format.\n");
    }

    /**
     * Overloaded constructor that prints the custom message.
     * @param s
     */
    public IncorrectFileFormatException(String s) {
        System.out.println(s);
    }
}
