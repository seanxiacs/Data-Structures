package Homework2;

/**
 * ItemList uses the ItemInfoNode class and creates a doubly linked list that can be operated on within this class.
 * @author Sean Xia, SBU ID 113181409, R30
 */

public class ItemList {
    private ItemInfoNode head;
    private ItemInfoNode tail;
    private ItemInfoNode tempCursor;
    private int numItems;

    String tableHeader = String.format("%-25s%-11s%-19s%-19s%-6s\n", "Item Name", "RFID", "Original Location", "Current Location", "Price") + new String(new char[80]).replace("\0", "-") + "\n";

    /**
     * Default constructor of the ItemInfoNode class.
     */
    public ItemList() {
        head = null;
        tail = null;
        tempCursor = null;
        numItems = 0;
    }

    /**
     * This method inserts the ItemInfo, and ItemInfoNode created from the inputted parameters and inserts it into the proper place in the list based on the RFID Tag Numbers.
     * O(N). This method is O(1) at best when there is no other node or if it is to be inserted at the beginning of the list and O(N) at worst because it always begins at the head of the doubly linked list if possible and loops through as many nodes as it has to until it can insert the node into the list. Because it has a single while loop, It is O(N) in big O notation.
     * @param name
     * @param rfidTag
     * @param price
     * @param initPosition
     * @throws InvalidDataException
     * @throws InvalidRfidTagNumberException
     * @throws InvalidOriginalLocationException
     * @throws InvalidCurrentLocationException
     * @throws InvalidCartException
     */
    public void insertInfo(String name, String rfidTag, double price, String initPosition) throws InvalidDataException, InvalidRfidTagNumberException, InvalidOriginalLocationException, InvalidCurrentLocationException, InvalidCartException {
        ItemInfo newItemInfo = new ItemInfo(name, price, rfidTag, initPosition, initPosition);
        ItemInfoNode newItemInfoNode = new ItemInfoNode(newItemInfo);

        if(head == null) {
            head = newItemInfoNode;
            tail = newItemInfoNode;
        }
        else {
            tempCursor = head;

            while(tempCursor != null) {
                long decimalTempCursor = Long.parseLong(tempCursor.getInfo().getRfidTagNumber(), 16);
                long decimalNewNode = Long.parseLong(newItemInfoNode.getInfo().getRfidTagNumber(), 16);
                if (decimalTempCursor > decimalNewNode) { //Places towards the end if there are identical RFIDs.
                    if(tempCursor == head) {
                        newItemInfoNode.setNext(tempCursor);
                        newItemInfoNode.getNext().setPrev(newItemInfoNode);
                        head = newItemInfoNode;
                    }
                    else {
                        newItemInfoNode.setPrev(tempCursor.getPrev());
                        newItemInfoNode.setNext(tempCursor);
                        newItemInfoNode.getPrev().setNext(newItemInfoNode); //Order of these two
                        newItemInfoNode.getNext().setPrev(newItemInfoNode); //Does not matter
                    }
                    break;
                }
                else {
                    if(tempCursor == tail) {
                        newItemInfoNode.setPrev(tempCursor);
                        newItemInfoNode.getPrev().setNext(newItemInfoNode);
                        tail = newItemInfoNode;
                        break;
                    }
                }
                tempCursor = tempCursor.getNext();
            }
        }
        numItems++;
    }

    /**
     * This method removes all of the nodes that have their current location set to out, indicating that the item has been bought.
     * O(N). This method is O(N) at best and worst because it always begins at the head of the doubly linked list loops through every node and checks their current location, removing the ones with location "out." Because it has a single while loop, It is O(N) in big O notation.
     * @throws EmptyStoreException
     * @throws NullNodeException
     */
    public void removeAllPurchased() throws EmptyStoreException, NullNodeException {
        if(numItems == 0) {
            throw new EmptyStoreException("There are no items in the department store to check.\n");
        }

        String removedTable = "The following item(s) have removed from the system: \n";
        removedTable += tableHeader;

        tempCursor = head;

        while(tempCursor != null) {
            if(tempCursor.getInfo().getCurrent_location().equalsIgnoreCase("out")) {
                removedTable += removeCursorNode().getInfo() + "\n";
            }
            else {
                tempCursor = tempCursor.getNext();
            }
        }

        System.out.println(removedTable);
    }

    /**
     * This method looks to see if there is an item with the entered parameters, and if there is, moves the item to the intended destination if it is valid.
     * O(N). This method is O(1) at best when the item that is to be moved is at the start of the list and O(N) at worst because it always begins at the head of the doubly linked list if possible and loops through as many nodes as it has to until it can find the right item to change the current location of. Because it has a single while loop, It is O(N) in big O notation.
     * @param rfidTag
     * @param source
     * @param dest
     * @return found
     * @throws InvalidSourceException
     * @throws InvalidCurrentLocationException
     * @throws InvalidCartException
     */
    public boolean moveItem(String rfidTag, String source, String dest) throws InvalidSourceException, InvalidCurrentLocationException, InvalidCartException {
        boolean found = false;

        tempCursor = head;

        if(source.equalsIgnoreCase("out")) {
            throw new InvalidSourceException("Cannot get an item that is out of the system.\n");
        }
        if(ItemInfo.isInvalidCurrentLocation(dest)) {
            throw new InvalidCurrentLocationException("The input is not a valid destination. The input must be \"out\", start with an \"s\" and followed by 5 digits, or start with \"c\" and followed by 3 digits.\n");
        }

        while(tempCursor != null) {
            String moveTempRfid = tempCursor.getInfo().getRfidTagNumber();
            String moveTempSource = tempCursor.getInfo().getCurrent_location();

            if(moveTempRfid.equalsIgnoreCase(rfidTag) && moveTempSource.equalsIgnoreCase(source)) {
                tempCursor.getInfo().setCurrent_location(dest);
                found = true;
                break;
            }
            else {
                tempCursor = tempCursor.getNext();
            }
        }

        return found;
    }

    /**
     * This method prints out all the items in the doubly linked list in a neatly formatted table.
     * O(N). This method is O(N) at best and worst because it always begins at the head of the doubly linked list and loops through all the nodes to add it to the String that is being printed with the table in it. Because it has a single while loop (in the toString(), It is O(N) in big O notation.
     */
    public void printAll() {
        System.out.println(toString());
    }

    /**
     * This method prints out a formatted table of all of the items in a current location by going through the entire list.
     * O(N). This method is O(N) at best and worst because it always begins at the head of the doubly linked list and loops through all the nodes, checks to see if it is at a current location, and if so, adds it to the String that will be printed in a neatly formatted table. Because it has a single while loop, It is O(N) in big O notation.
     * @param location
     * @throws InvalidCurrentLocationException
     * @throws InvalidCartException
     */
    public void printByLocation(String location) throws InvalidCurrentLocationException, InvalidCartException {
        if(ItemInfo.isInvalidCurrentLocation(location)) {
            throw new InvalidCurrentLocationException("The inputted location is invalid. The input must be \"out\", start with an \"s\" and followed by 5 digits, or start with \"c\" and followed by 3 digits.\n");
        }

        String locationTable = "The following item(s) are in the entered location: \n";
        locationTable += tableHeader;

        tempCursor = head;

        while(tempCursor != null) {
            if(tempCursor.getInfo().getCurrent_location().equalsIgnoreCase(location)) {
                locationTable += tempCursor.getInfo() + "\n"; //Must be sorted in order of rfidTagNumber,  but I assume that since it is already presorted and going forward, it will be sorted.
            }
            tempCursor = tempCursor.getNext();
        }

        System.out.println(locationTable);
    }

    /**
     * This method goes through the list and checks to see if the original and current location are the same. If not, it moves the item back to the original location.
     * O(N). This method is O(N) at best and worst because it always begins at the head of the doubly linked list and loops through all the nodes, checks to see if current location and original location are the same, and if they aren't the same, moves the item back to original location. Because it has a single while loop, It is O(N) in big O notation.
     * @throws InvalidCurrentLocationException
     * @throws InvalidCartException
     */
    public void cleanStore() throws InvalidCurrentLocationException, InvalidCartException {
        String cleanTable = "The following item(s) have been moved back to their original locations: \n";
        cleanTable += tableHeader;

        tempCursor = head;

        while(tempCursor != null) {
            if(tempCursor.getInfo().getCurrent_location().substring(0,1).equalsIgnoreCase("s")) {
                if(!tempCursor.getInfo().getOriginal_location().equalsIgnoreCase(tempCursor.getInfo().getCurrent_location())) {
                    cleanTable += tempCursor.getInfo() + "\n"; //Must be sorted in order of rfidTagNumber,  but I assume that since it is already presorted and going forward, it will be sorted.
                    tempCursor.getInfo().setCurrent_location(tempCursor.getInfo().getOriginal_location());
                }
            }
            tempCursor = tempCursor.getNext();
        }

        System.out.println(cleanTable);
    }

    /**
     * This method moves all of the items in the entered cart to "out" to indicate that the item has been bought.
     * O(N). This method is O(N) at best and worst because it always begins at the head of the doubly linked list and loops through all the nodes, checks to see if current location is the cart, and if sp, moves the item to "out." Because it has a single while loop, It is O(N) in big O notation.
     * @param cartNumber
     * @return checkOutSum
     * @throws InvalidCartException
     * @throws InvalidCurrentLocationException
     */
    public double checkOut(String cartNumber) throws InvalidCartException, InvalidCurrentLocationException {
        String checkOutTable = "The following item(s) have been checked out of the cart: \n";
        checkOutTable += tableHeader;
        double checkOutSum = 0;

        if(cartNumber.length() != 4 || !cartNumber.substring(0,1).equalsIgnoreCase("c") || !cartNumber.substring(1, 4).matches("[0-9]+")) {
            throw new InvalidCartException("This input is not considered a valid cart.\n");
        }

        tempCursor = head;

        while(tempCursor != null) {
            if(tempCursor.getInfo().getCurrent_location().equalsIgnoreCase(cartNumber)) {
                checkOutSum += tempCursor.getInfo().getPrice();

                checkOutTable += tempCursor.getInfo() + "\n"; //Must be sorted in order of rfidTagNumber,  but I assume that since it is already presorted and going forward, it will be sorted.
                tempCursor.getInfo().setCurrent_location("out");
            }
            tempCursor = tempCursor.getNext();
        }

        System.out.println(checkOutTable);

        return checkOutSum;
    }

    /**
     * Method that will remove the node that the tempCursor is currently at. It is a helper method for removeAllPurchased method.
     * @return removedNode
     * @throws NullNodeException
     */
    public ItemInfoNode removeCursorNode() throws NullNodeException {
        ItemInfoNode removedNode;
        //Add temp variable to be returned if we want the thing that will be removed.
        if(tempCursor == null) {
            throw new NullNodeException("There is no node that is to be removed.\n");
        }
        else if(tempCursor == head && tempCursor == tail) {
            removedNode = tempCursor;
            head = null;
            tail = null;
            tempCursor = null;
        }
        else if(tempCursor == head) {
            removedNode = tempCursor;
            head = head.getNext();
            head.setPrev(null);
            tempCursor = head;
        }
        else if(tempCursor == tail) {
            removedNode = tempCursor;
            tail = tail.getPrev();
            tail.setNext(null);
            tempCursor = null;
        }
        else {
            removedNode = tempCursor;
            tempCursor.getPrev().setNext(tempCursor.getNext());
            tempCursor.getNext().setPrev(tempCursor.getPrev());
            tempCursor = tempCursor.getNext();
        }

        numItems--;
        return removedNode;
    }

    /**
     * This method prints all of the items with a certain RFID Tag Number.
     * @param rfidTagNumber
     * @throws InvalidRfidTagNumberException
     */
    public void printByRfidTagNumber(String rfidTagNumber) throws InvalidRfidTagNumberException {
        if((rfidTagNumber.length() != 9) || ItemInfo.isNotHexadecimal(rfidTagNumber)) {
            throw new InvalidRfidTagNumberException("The RFID number is either not 9 characters long or is not a digit from 0-9 or a letter from A-F.\n");
        }

        String printByRfidTagNumberTable = "The following item(s) have the RFID Tag Number: \n";
        printByRfidTagNumberTable += tableHeader;

        tempCursor = head;

        while(tempCursor != null) {
            if(tempCursor.getInfo().getRfidTagNumber().equalsIgnoreCase(rfidTagNumber)) {
                printByRfidTagNumberTable += tempCursor.getInfo() + "\n";
            }
            tempCursor = tempCursor.getNext();
        }

        System.out.println(printByRfidTagNumberTable);
    }

    /**
     * The ItemList toString method returns information in ItemList in an ordered fashion.
     * @return formattedString
     */
    @Override
    public String toString() {
        String table;

        table = tableHeader;

        tempCursor = head;

        while(tempCursor != null) {
            table += tempCursor.getInfo();
            table += "\n"; //Can shorten by adding this to previous statement.
            tempCursor = tempCursor.getNext();
        }

        return table;
    }
}
