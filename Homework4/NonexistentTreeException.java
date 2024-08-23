package Homework4;

/**
 * NonexistentTreeException is thrown trying to perform an action on a tree that does not exist.
 * @author Sean Xia, SBU ID 113181409, sean.xia@stonybrook.edu, HW#4, CSE214, R30, TAs: Charles Clark, Amogh Joshi, Sharfuddin Mohammed, Vinayak Shenoy.
 */

public class NonexistentTreeException extends Exception {
    /**
     * Default constructor of the NonexistentTreeException class.
     */
    public NonexistentTreeException() {
        System.out.println("The tree does not exist.\n");
    }

    /**
     * Overloaded constructor that prints the custom message.
     * @param s
     */
    public NonexistentTreeException(String s) {
        System.out.println(s);
    }
}
