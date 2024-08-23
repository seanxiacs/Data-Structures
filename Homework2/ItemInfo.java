package Homework2;

/**
 * ItemInfo holds information about the items such as RFID, original and current location, price, and name.
 * @author Sean Xia, SBU ID 113181409, R30
 */

public class ItemInfo {
    private String name;
    private double price;
    private String rfidTagNumber;
    private String original_location;
    private String current_location;

    /**
     * Default constructor of the ItemInfo class.
     */
    public ItemInfo() {
    }

    /**
     * Overloaded constructor that sets all the information about the item, and makes sure that the preconditions are valid.
     * @param newName
     * @param newPrice
     * @param newRfidTagNumber
     * @param newOriginal_location
     * @param newCurrent_location
     * @throws InvalidRfidTagNumberException
     * @throws InvalidOriginalLocationException
     * @throws InvalidCurrentLocationException
     * @throws InvalidCartException
     */
    public ItemInfo(String newName, double newPrice, String newRfidTagNumber, String newOriginal_location, String newCurrent_location) throws InvalidRfidTagNumberException, InvalidOriginalLocationException, InvalidCurrentLocationException, InvalidCartException {
        if((newRfidTagNumber.length() != 9) || isNotHexadecimal(newRfidTagNumber)) { //Second statement will not be evaluate so no problem or errors with second statement.
            throw new InvalidRfidTagNumberException("The RFID number is either not 9 characters long or is not a digit from 0-9 or a letter from A-F.\n");
        }
        if((newOriginal_location.length() != 6) || isInvalidOriginalLocation(newOriginal_location)) {
            throw new InvalidOriginalLocationException("The input is not a valid original location. The first character must be s followed by 5 digits.\n");
        }
        if(isInvalidCurrentLocation(newCurrent_location)) {
            throw new InvalidCurrentLocationException("The input is not a valid original location. The input must be \"out\", start with an \"s\" and followed by 5 digits, or start with \"c\" and followed by 3 digits.\n");
        }

        this.name = newName;
        this.price = newPrice;
        this.rfidTagNumber = newRfidTagNumber;
        this.original_location = newOriginal_location;
        this.current_location = newCurrent_location;
    }

    /**
     * This method sets the name.
     * @param newName
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * This method sets the price.
     * @param newPrice
     */
    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    /**
     * This method sets the RFID Tag Number.
     * @param newRfidTagNumber
     * @throws InvalidRfidTagNumberException
     */
    public void setRfidTagNumber(String newRfidTagNumber) throws InvalidRfidTagNumberException {
        if((newRfidTagNumber.length() != 9) || isNotHexadecimal(newRfidTagNumber)) {
            throw new InvalidRfidTagNumberException("The RFID number is either not 9 characters long or is not a digit from 0-9 or a letter from A-F.\n");
        }

        this.rfidTagNumber = newRfidTagNumber;
    }

    /**
     * This method sets the original location.
     * @param newOriginal_location
     * @throws InvalidOriginalLocationException
     */
    public void setOriginal_location(String newOriginal_location) throws InvalidOriginalLocationException {
        if((newOriginal_location.length() != 6) || isInvalidOriginalLocation(newOriginal_location)) {
            throw new InvalidOriginalLocationException("The input is not a valid original location. The first character must be s followed by 5 digits.\n");
        }

        this.original_location = newOriginal_location;
    }

    /**
     * This method sets the current location.
     * @param newCurrent_location
     * @throws InvalidCurrentLocationException
     */
    public void setCurrent_location(String newCurrent_location) throws InvalidCurrentLocationException {
        if(isInvalidCurrentLocation(newCurrent_location)) {
            throw new InvalidCurrentLocationException("The input is not a valid original location. The input must be \"out\", start with an \"s\" and followed by 5 digits, or start with \"c\" and followed by 3 digits.\n");
        }

        this.current_location = newCurrent_location;
    }

    /**
     * This method gets the name.
     * @return this.name
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method gets the price.
     * @return this.price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * This method gets the RFID Tag Number.
     * @return this.rfidTagNumber
     */
    public String getRfidTagNumber() {
        return this.rfidTagNumber;
    }

    /**
     * This method gets the original location.
     * @return this.original_location
     */
    public String getOriginal_location() {
        return this.original_location;
    }

    /**
     * This method gets the current location.
     * @return this.current_location
     */
    public String getCurrent_location() {
        return this.current_location;
    }

    /**
     * The ItemInfo toString method returns information in ItemInfo in an ordered fashion.
     * @return formattedString
     */
    @Override
    public String toString() {
        String formattedString = String.format("%-25s%-11s%-19s%-19s%-6.2f", this.name, this.rfidTagNumber, this.original_location, this.current_location, this.price);

        return formattedString;
    }

    /**
     * Static method that identifies whether the characters that are in the string parameter consist of valid characters.
     * @param s
     * @return True or False based off of if the string consists of letters A-F and digits 0-9.
     */
    public static boolean isNotHexadecimal(String s) {
        if(s.matches("-?[0-9a-fA-F]+")) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Method that identifies whether the theoretical location string is an allowed original location.
     * @param s
     * @return True or False based off if the string starts with "s" and is followed by 5 more digits.
     */
    public boolean isInvalidOriginalLocation(String s) {
        if(s.substring(0, 1).equalsIgnoreCase("s") && s.substring(1, 6).matches("[0-9]+")) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Static method that identifies whether the supposed string is a possible current location.
     * @param s
     * @return True or false based off of if the string is a valid current location.
     */
    public static boolean isInvalidCurrentLocation(String s) {
        if((s.length() == 4 && s.substring(0,1).equalsIgnoreCase("c") && s.substring(1, 4).matches("[0-9]+")) || (s.length() == 6 && s.substring(0,1).equalsIgnoreCase("s") && s.substring(1, 6).matches("[0-9]+")) || s.equalsIgnoreCase("out")) {
            return false;
        }
        else {
            return true;
        }
    }
}

