package Homework2;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * DepartmentStore uses the ItemList class and the doubly linked list that is in the ItemList class and manipulates it using the methods in the ItemList class. DepartmentStore also uses the user's inputs to perform operations involving a doubly linked list of items.
 * @author Sean Xia, SBU ID 113181409, R30
 */

public class DepartmentStore {
    private static final Scanner input = new Scanner(System.in);
    private static final Scanner inputString = new Scanner(System.in);
    private static String menuOption;
    private static ItemList items = new ItemList();

    /**
     * This method runs until the user quits, performing actions that are provided in a menu option.
     * @param args
     * @throws EmptyStoreException
     * @throws InvalidDataException
     * @throws NullNodeException
     * @throws InvalidRfidTagNumberException
     * @throws InvalidOriginalLocationException
     * @throws InvalidCurrentLocationException
     * @throws InvalidSourceException
     * @throws InvalidCartException
     */
    public static void main(String[] args) throws EmptyStoreException, InvalidDataException, NullNodeException, InvalidRfidTagNumberException, InvalidOriginalLocationException, InvalidCurrentLocationException, InvalidSourceException, InvalidCartException {
        System.out.println("Welcome!\n");

        do {
            try {
                displayMenu();
                System.out.print("Select a menu option: ");
                menuOption = input.next().toLowerCase();
                System.out.println();

                switch (menuOption) {
                    case "c":
                        items.cleanStore();
                        break;
                    case "i":
                        System.out.print("Enter the name: ");
                        String insertName = inputString.nextLine();
                        System.out.print("Enter the RFID: ");
                        String insertRfid = inputString.nextLine();
                        System.out.print("Enter the original location: ");
                        String insertOriginalLocation = inputString.nextLine();
                        System.out.print("Enter the price: ");
                        double insertPrice = input.nextDouble();
                        System.out.println();

                        items.insertInfo(insertName, insertRfid.toUpperCase(), insertPrice, insertOriginalLocation);
                        break;
                    case "l":
                        System.out.print("Enter the location: ");
                        String listByLocation = inputString.nextLine();

                        items.printByLocation(listByLocation);
                        break;
                    case "m":
                        System.out.print("Enter the RFID: ");
                        String moveRfid = inputString.nextLine();
                        System.out.print("Enter the original location: ");
                        String moveOriginalLocation = inputString.nextLine();
                        System.out.print("Enter the new location: ");
                        String moveNewLocation = inputString.nextLine();
                        System.out.println();

                        if(!items.moveItem(moveRfid.toUpperCase(), moveOriginalLocation, moveNewLocation)) {
                            System.out.println("There was no item found with that RFID and source location.\n");
                        }
                        break;
                    case "o":
                        System.out.print("Enter the cart number: ");
                        String checkoutCartNumber = inputString.nextLine();
                        System.out.println();

                        //Might want to change the output format to match sample here where is says cart 105 instead of c105 etc.
                        System.out.println("The total cost for all merchandise in " + checkoutCartNumber + " was $" + String.format("%.2f", items.checkOut(checkoutCartNumber)) + "\n"); //String.format("%-.2f", items.checkOut(checkoutCartNumber))
                        break;
                    case "p":
                        items.printAll();
                        break;
                    case "r": //Fix
                        System.out.print("Enter the RFID: ");
                        String printByRfid = inputString.nextLine();

                        items.printByRfidTagNumber(printByRfid);
                        break;
                    case "u":
                        items.removeAllPurchased();
                        break;
                    case "q":
                        System.out.println("Program terminating normally...");
                        break;
                    default:
                        System.out.println("You did not select a valid menu option. Please try again.\n");
                        break; //Not needed after the default but recommended.
                }
            }
            catch(NumberFormatException e) {
                System.out.println("It seems that the hexadecimal you entered is too large for a long type.");
            }
            catch(EmptyStoreException e) {
            }
            catch(InvalidCartException e) {
            }
            catch(InvalidOriginalLocationException e) {
            }
            catch(InvalidCurrentLocationException e) {
            }
            catch(InvalidDataException e) {
            }
            catch(InvalidRfidTagNumberException e) {
            }
            catch(InvalidSourceException e) {
            }
            catch(NullNodeException e) {
            }
            catch(IllegalArgumentException e) {
                System.out.println("Please enter a position that is within the valid range next time.\n");
            }
            catch(InputMismatchException e) {
                System.out.println("Please enter an argument of the right type.\n");
            }
            catch(NullPointerException e) {
                System.out.println("There seems to be a null-pointer exception occurring.");
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
                "    C - Clean store \n" +
                "    I - Insert an item into the list \n" +
                "    L - List by location \n" +
                "    M - Move an item in the store \n" +
                "    O - Checkout  \n" +
                "    P - Print all items in store \n" +
                "    R - Print by RFID tag number         (optional - extra credit)         (implemented)\n" +
                "    U - Update inventory system   \n" +
                "    Q - Exit the program.   \n";
        System.out.println(menu);
    }
}
