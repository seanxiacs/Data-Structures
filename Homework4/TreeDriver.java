package Homework4;

/**
 * TreeDriver holds the main method and uses the Tree and TreeNode class to perform actions after loading a tree from a text file.
 * @author Sean Xia, SBU ID 113181409, sean.xia@stonybrook.edu, HW#4, CSE214, R30, TAs: Charles Clark, Amogh Joshi, Sharfuddin Mohammed, Vinayak Shenoy.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TreeDriver {
    private static final Scanner input = new Scanner(System.in);
    private static final Scanner inputString = new Scanner(System.in);
    private static String menuOption;
    private static Tree newTree = new Tree();
    private static String tempParentLabel;
    private static int expectedChildren = 0;

    /**
     * This method runs until the user quits, performing actions that are provided in a menu option.
     * @param args
     * @throws IncorrectFileFormatException
     * @throws IncorrectLabelException
     * @throws NonexistentTreeException
     */
    public static void main(String[] args) throws IncorrectFileFormatException, IncorrectLabelException, NonexistentTreeException {
        System.out.println("Welcome!\n");

        do {
            try {
                Scanner sc;
                displayMenu();
                System.out.print("Select a menu option: ");
                menuOption = input.next().toLowerCase();
                System.out.println();

                switch(menuOption) {
                    case "l":
                        System.out.print("Enter the file name: ");
                        String fileName = inputString.nextLine();

                        File file = new File(fileName);
                        sc = new Scanner(file);

                        String tempLabel = null;
                        String tempPrompt = null;
                        String tempMessage = null;
                        String tempLine = null;
                        String[] expected;

                        while(sc.hasNextLine()) {
                            tempLine = sc.nextLine().trim();

                            //root
                            if(tempLine.equalsIgnoreCase("root")) {
                                tempLabel = tempLine;

                                if(sc.hasNextLine()) {
                                    tempPrompt = sc.nextLine().trim();
                                } else {
                                    throw new IncorrectFileFormatException("There was a problem trying to add the root node.\n");
                                }

                                if(sc.hasNextLine()) {
                                    tempMessage = sc.nextLine().trim();
                                } else {
                                    throw new IncorrectFileFormatException("There was a problem trying to add the root node.\n");
                                }

                                newTree.addNode(tempLabel, tempPrompt, tempMessage, null);
                            }
                            else {
                                expected = tempLine.split("\\s+");

                                if (expected.length != 2) {
                                    throw new IncorrectFileFormatException("There was a problem in the format of giving the parent node and the expected number of children.\n");
                                } else {
                                    tempParentLabel = expected[0];
                                    expectedChildren = Integer.parseInt(expected[1]);
                                }

                                for (int i = 0; i < expectedChildren; i++) {
                                    if (sc.hasNextLine()) {
                                        tempLabel = sc.nextLine().trim();
                                    } else {
                                        throw new IncorrectFileFormatException("There was a problem trying to add the child. The likely error is that there were no more lines to read because of a format error.\n");
                                    }

                                    if (sc.hasNextLine()) {
                                        tempPrompt = sc.nextLine().trim();
                                    } else {
                                        throw new IncorrectFileFormatException("There was a problem trying to add the child. The likely error is that there were no more lines to read because of a format error.\n");
                                    }

                                    if (sc.hasNextLine()) {
                                        tempMessage = sc.nextLine().trim();
                                    } else {
                                        throw new IncorrectFileFormatException("There was a problem trying to add the child. The likely error is that there were no more lines to read because of a format error.\n");
                                    }

                                    newTree.addNode(tempLabel, tempPrompt, tempMessage, tempParentLabel);
                                }
                            }
                        }

                        System.out.println("Tree created successfully!\n");

                        break;
                    case "h":
                        System.out.println("Help Session Starting... ");

                        newTree.beginSession();

                        System.out.println("Thank you for using this automated help service!\n");
                        break;
                    case "t":
                        System.out.println("Traversing the tree in preorder: ");

                        if(newTree.getRoot() != null) {
                            newTree.preOrder();
                        }
                        else {
                            System.out.println("This tree does not exist.\n");
                        }
                        break;
                    case "q":
                        System.out.println("Thank you for using our automated help services! (Program terminated normally...)"); //Program terminating normally...
                        break;
                    default:
                        System.out.println("You did not select a valid menu option. Please try again.\n");
                        break; //Not needed after the default but recommended.
                }
            }
            catch(IncorrectFileFormatException e) {
            }
            catch(IncorrectLabelException e) {
            }
            catch(NonexistentTreeException e) {
            }
            catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("There was a problem with the index of an array.");
            }
            catch(NumberFormatException e) {
                System.out.println("It seems that you did not give the number of children expected for this node.");
            }
            catch(InputMismatchException e) {
                System.out.println("Please enter an argument of the right type.\n");
                input.next();
            }
            catch(NoSuchElementException e) {
                    System.out.println("There is a no such element exception occurring.\n");
            }
            catch(FileNotFoundException e) {
                System.out.println("The file could not be found.\n");
            }
            catch(IllegalArgumentException e) {
                System.out.println("Please enter a position that is within the valid range next time.\n");
            }
            catch(NullPointerException e) {
                System.out.println("There seems to be a null-pointer exception occurring.\n");
            }
            catch(Exception e) {
                System.out.println("There seems to be an error that was not entirely accounted for.\n");
            }
        }
        while(menuOption.compareToIgnoreCase("q") != 0);

        input.close();
        inputString.close();
    }

    /**
     * This method prints the menu for the user to see the operations that can be performed.
     */
    public static void displayMenu() {
        String menu = "Options Menu: \n" +
                "L - Load a Tree.\n" +
                "H - Begin a Help Session.\n" +
                "T - Traverse the Tree in Preorder.\n" +
                "Q - Quit\n";
        System.out.println(menu);
    }
}
