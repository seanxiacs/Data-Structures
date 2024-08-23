package Homework4;

/**
 * IncorrectLabelException is thrown when while loading the tree, the program finds an error with the a label while trying to add the node to the tree. Some possible errors are trying to add without filling the left-most side first or giving an incorrect for a node.
 * @author Sean Xia, SBU ID 113181409, sean.xia@stonybrook.edu, HW#4, CSE214, R30, TAs: Charles Clark, Amogh Joshi, Sharfuddin Mohammed, Vinayak Shenoy.
 */

public class IncorrectLabelException extends Exception {
    /**
     * Default constructor of the IncorrectLabelException class.
     */
    public IncorrectLabelException() {
        System.out.println("The node's label is incorrect.\n");
    }

    /**
     * Overloaded constructor that prints the custom message.
     * @param s
     */
    public IncorrectLabelException(String s) {
        System.out.println(s);
    }
}
