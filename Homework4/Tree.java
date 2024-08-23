package Homework4;

/**
 * Tree holds creates a tree after taking inputs from the TreeDriver class and encapsulates the information using the TreeNode class.
 * @author Sean Xia, SBU ID 113181409, sean.xia@stonybrook.edu, HW#4, CSE214, R30, TAs: Charles Clark, Amogh Joshi, Sharfuddin Mohammed, Vinayak Shenoy.
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class Tree {
    private TreeNode root;
    TreeNode tempNode = null;

    /**
     * Default constructor of the Tree class.
     */
    public Tree() {
        this.root = null;
    }

    /**
     * This method adds a node to the tree after checking that the preconditions are valid. Some preconditions are that we should add from left to right and label should be correct.
     * @param label
     * @param prompt
     * @param message
     * @param parentLabel
     * @return True or False based off the node could be added successfully.
     * @throws IncorrectLabelException
     */
    public boolean addNode(String label, String prompt, String message, String parentLabel) throws IncorrectLabelException {
        TreeNode newNode = new TreeNode(label, message, prompt);

            //Make sure to account for the root.
        if(root == null && label.equalsIgnoreCase("root")) {
            root = newNode;
            return true;
        }

        //Did not account for just having the correct last number of the label making the code correct.
        //This breaks the tree.


        TreeNode parentNode = getNodeReference(parentLabel);

        if(parentNode == null) {
            return false;
        }

        for(int i = 0; i < parentNode.getChildren().length; i++) {
            if(parentNode.getCertainChild(i) == null) {
                if(i != Character.getNumericValue(label.charAt(label.length() - 1)) - 1) {
                    throw new IncorrectLabelException("The label for the node is incorrect.\n");
                }

                //Tried to account for the problem that text file has the correct last number for label but keep running into errors.
                /*
                if(!(parentLabel.equalsIgnoreCase("root") || parentLabel == null)) {
                    if(!label.substring(0, label.length() - 2).equalsIgnoreCase(parentLabel)) {
                        throw new IncorrectLabelException("The label for the node is incorrect.\n");
                    }
                    parentNode.setChild(newNode, i);
                    return true;
                }

                 */

                parentNode.setChild(newNode, i);
                return true;
            }
        }

        return false;
    }

    /**
     * This method finds the node that has the same reference as the parameter and returns it using a recursive method in the TreeNode class.
     * @param label
     * @return root.getNodeReferenceNode(label)
     */
    public TreeNode getNodeReference(String label) {
        if (root == null) {
            return null;
        }
        else {
            return root.getNodeReferenceNode(label);
        }
    }

    /**
     * This method calls a recursive method in the TreeNode class to print the tree using preorder traversal.
     * @throws NonexistentTreeException
     */
    public void preOrder() throws NonexistentTreeException {
        if(root == null) {
            throw new NonexistentTreeException("This tree does not exist.\n");
        }

        root.preOrderNode();
    }

    /**
     * This method allows the user to begin going through the tree to solve their issue.
     * @throws NonexistentTreeException
     */
    public void beginSession() throws NonexistentTreeException {
        if(root == null) {
            throw new NonexistentTreeException("This tree does not exist.\n");
        }

        Scanner inputChoice = new Scanner(System.in);
        tempNode = root;
        int choice = -1;

        do {
            try {
                displayOptions();
                System.out.print("Choice: ");
                choice = inputChoice.nextInt();
                System.out.println();



                if(choice != 0) {
                    if(choice > 0 && choice <= 3 && tempNode.getCertainChild(choice - 1) != null) {
                        tempNode = tempNode.getCertainChild(choice - 1);
                        if(tempNode.isLeaf()) {
                            System.out.println(tempNode.getMessage() + "\n");
                        }
                    }
                    else {
                        System.out.println("You did not select a valid choice. Please try again.\n");
                    }
                }

            }
            catch(NullPointerException e) {
                System.out.println("There seems to be a null-pointer exception occurring.\n");
            }
            catch(IllegalArgumentException e) {
                System.out.println("Please enter a position that is within the valid range next time.\n");
            }
            catch(InputMismatchException e) {
                System.out.println("Please enter an argument of the right type.\n");
                inputChoice.next(); //This needs to be done to remove the token caused by an InputMismatchException
            }
            catch(Exception e) {
                System.out.println("There seems to be an error that was not entirely accounted for.\n");
            }
        }
        while(!tempNode.isLeaf() && choice != 0);

        //inputChoice.close();
        //This causes problems with the other class scanners.
    }

    /**
     * This method gets the root.
     * @return root
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * This method prints the message and the following options that the user has.
     */
    public void displayOptions() {
        System.out.println(tempNode.getMessage());

        for(int i = 0; i < tempNode.getChildren().length; i++) {
            if (tempNode.getCertainChild(i) != null) {
                System.out.println((i + 1) + " " + tempNode.getCertainChild(i).getPrompt());
            }
        }

        System.out.println("0 Exit Session.");
    }
}
