package Homework5;

/**
 * Mailbox has the main method that runs the email system and also has other supporting methods for the main method.
 * @author Sean Xia, SBU ID 113181409, sean.xia@stonybrook.edu, HW#4, CSE214, R30, TAs: Charles Clark, Amogh Joshi, Sharfuddin Mohammed, Vinayak Shenoy.
 */

import java.io.*;
import java.util.*;

public class Mailbox implements Serializable {
    private static final Scanner input = new Scanner(System.in);
    private static final Scanner inputString = new Scanner(System.in);
    private static String menuOption;
    private static String subMenuOption;

    private Folder inbox = new Folder("Inbox");
    private Folder trash = new Folder("Trash");
    private ArrayList<Folder> folders = new ArrayList<>(Arrays.asList(inbox, trash));

    public static Mailbox mailbox;

    /**
     * This method runs until the user quits, performing actions that are provided in a menu option.
     * @param args
     */
    public static void main(String[] args) {
        mailbox = new Mailbox();

        //ObjectInputStream.readObject():
        //Email myObject; //MyClass implements Serializable

        Mailbox myObject;
        try {
            //If file is found, open it
            FileInputStream file = new FileInputStream("mySaveFile.obj");
            ObjectInputStream fin = new ObjectInputStream(file);
            myObject = (Mailbox) fin.readObject();
            fin.close();
            file.close();
            if(myObject != null) {
                mailbox = myObject;
                System.out.println("Previous save found, previous save loaded.\n");
            }

        } catch(IOException a){
            System.out.println("Previous save not found, starting with an empty mailbox.\n");
        }
        catch(ClassNotFoundException c){
            System.out.println("Previous save not found, starting with an empty mailbox.\n");
        }
        catch(Exception e) {
            System.out.println("Error reading file.");
        }

        //public static
        System.out.println("Welcome!\n");

        do {
            try {
                //public static Mailbox mailbox = this;
                //mailbox = this;
                displayMailbox();
                displayMenu();
                System.out.print("Select a menu option: ");
                menuOption = input.next().toLowerCase();
                System.out.println();

                switch (menuOption) {
                    case "a":
                        System.out.print("Enter folder name: ");
                        String newFolderName = inputString.nextLine();
                        System.out.println();

                        mailbox.addFolder(new Folder(newFolderName));
                        break;
                    case "r":
                            System.out.print("Enter the name of the folder you want to remove: ");
                        String removeFolderName = inputString.nextLine();
                        System.out.println();

                        mailbox.deleteFolder(removeFolderName);
                        break;
                    case "c":
                        mailbox.composeEmail();
                        break;
                    case "f":
                        System.out.print("Enter the name of the folder you want to view: ");
                        String viewFolderName = inputString.nextLine();
                        System.out.println();

                        Folder foundFolder = mailbox.getFolder(viewFolderName);

                        if(foundFolder != null) {
                            //System.out.println(foundFolder);

                            subMenuActions(foundFolder);
                        }
                        break;
                    case "i":
                        //System.out.println(mailbox.inbox);

                        subMenuActions(mailbox.inbox);
                        break;
                    case "t":
                        //System.out.println(mailbox.trash);

                        subMenuActions(mailbox.trash);
                        break;
                    case "e":
                        mailbox.clearTrash();
                        break;
                    case "q":
                        //ObjectOutputStream.writeObject():
                        try {
                            FileOutputStream file = new FileOutputStream("mySaveFile.obj");
                            ObjectOutputStream fout = new ObjectOutputStream(file);
                            fout.writeObject(mailbox);
                            fout.close();
                            file.close();
                            System.out.println("Program successfully exited and mailbox saved. (Program terminated normally...)"); //Program terminating normally...
                        } catch(IOException a) {
                            System.out.println("Error occurred saving file.");
                        }
                        break;
                    default:
                        System.out.println("You did not select a valid menu option. Please try again.\n");
                        break; //Not needed after the default but recommended.
                }
            } catch(SameFolderNameException e) {
            } catch(NullParameterException e) {
            } catch(UndeletableFolderException e) {
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("There was a problem with the index of an array.\n");
            } catch(IndexOutOfBoundsException e) {
                System.out.println("There was an index out of bounds exception.\n");
            } catch(NumberFormatException e) {
                System.out.println("It seems that there was a number format exception.\n");
            } catch(InputMismatchException e) {
                System.out.println("Please enter an argument of the right type.\n");
                input.next();
            } catch(NoSuchElementException e) {
                System.out.println("There is a no such element exception occurring.\n");
            } catch(IllegalArgumentException e) {
                System.out.println("Please enter a position that is within the valid range next time.\n");
            } catch(NullPointerException e) {
                System.out.println("There seems to be a null-pointer exception occurring.\n");
            } catch(Exception e) {
                System.out.println("There seems to be an error that was not entirely accounted for.\n");
            }
        }
        while (menuOption.compareToIgnoreCase("q") != 0);

        input.close();
        inputString.close();
    }

    /**
     * This method adds a folder making sure that no folder with the same name already exists.
     * @param folder
     * @throws SameFolderNameException
     * @throws NullParameterException
     */
    public void addFolder(Folder folder) throws SameFolderNameException, NullParameterException {
        if(folder == null) {
            throw new NullParameterException("The folder being added is null.\n");
        }

        for(int i = 0; i < folders.size(); i++) {
            if(folders.get(i).getName().equalsIgnoreCase(folder.getName())) {
                throw new SameFolderNameException("A folder with the same name already exists within the mailbox.\n");
            }
        }

        folders.add(folder);
    }

    /**
     * This method deletes the folder, making sure that the folder is not the inbox or trash and informing the user if the folder was not found.
     * @param name
     * @throws UndeletableFolderException
     */
    public void deleteFolder(String name) throws UndeletableFolderException {
        if(name.equalsIgnoreCase("inbox") || name.equalsIgnoreCase("trash")) {
            throw new UndeletableFolderException("Cannot delete inbox or trash folder.\n");
        }

        boolean removed = false;
        for(int i = 0; i < folders.size(); i++) {
            if(folders.get(i).getName().equalsIgnoreCase(name)) {
                folders.remove(i);
                removed = true;
                break;
            }
        }
        if(!removed) {
            System.out.println("No folder was removed because a folder with this name was not found.\n");
        }
        else {
            System.out.println("Folder successfully removed.\n");
        }
    }

    /**
     * This method allows the user to create an email that will be added to the inbox folder.
     */
    public void composeEmail() {
        System.out.print("Enter recipient (To): ");
        String composeTo = inputString.nextLine();
        System.out.print("Enter carbon copy recipients (CC): ");
        String composeCC = inputString.nextLine();
        System.out.print("Enter blind carbon copy recipients (BCC): ");
        String composeBCC = inputString.nextLine();
        System.out.print("Enter subject line: ");
        String composeSubject = inputString.nextLine();
        System.out.print("Enter body: ");
        String composeBody = inputString.nextLine();
        System.out.println();

        Email composeEmail = new Email(composeTo, composeCC,composeBCC, composeSubject, composeBody, new GregorianCalendar());
        inbox.addEmail(composeEmail);
        System.out.println("Email successfully added to Inbox.\n");
    }

    /**
     * This method deletes the email, meaning it moves the email to the trash folder. If a folder in trash is deleted, or moved to trash, nothing changes.
     * @param email
     */
    public void deleteEmail(Email email) {
        moveEmail(email, trash);
    }

    /**
     * This method clears the trash and prints the number of emails removed from the trash.
     */
    public void clearTrash() {
        System.out.println(trash.getEmails().size() + " item(s) successfully deleted.\n");
        trash.getEmails().clear();
    }

    /**
     * This method moves the email
     * @param email
     * @param target
     */
    public void moveEmail(Email email, Folder target) {
        for(int i = 0; i < folders.size(); i++) {
            if(folders.get(i).getName().equalsIgnoreCase(target.getName())) {
                folders.get(i).addEmail(email);
            }
        }
    }

    /**
     * This method searches for and returns the folder with the name entered.
     * @param name
     * @return folders.get(i)
     */
    public Folder getFolder(String name) {
        for(int i = 0; i < folders.size(); i++) {
            if(folders.get(i).getName().equalsIgnoreCase(name)) {
                return folders.get(i);
            }
        }

        System.out.println("No folder with this name was not found so null is returned.\n");
        return null;
    }

    /**
     * This method prints the menu for the user to see the operations that can be performed.
     */
    public static void displayMenu() {
        String menu = "Options Menu: \n" +
                "A – Add folder\n" +
                "R – Remove folder\n" +
                "C – Compose email\n" +
                "F – Open folder\n" +
                "I – Open Inbox\n" +
                "T – Open Trash\n" +
                "E - Empty Trash\n" +
                "Q – Quit\n";
        System.out.println(menu);
    }

    /**
     * This method prints the submenu for the user to see the operations that can be performed.
     */
    public static void displaySubmenu() {
        String submenu = "Options Submenu\n" +
                "M – Move email\n" +
                "D – Delete email\n" +
                "V – View email contents\n" +
                "SA – Sort by subject line in ascending order\n" +
                "SD – Sort by subject line in descending order\n" +
                "DA – Sort by date in ascending order\n" +
                "DD – Sort by date in descending order\n" +
                "R – Return to mailbox\n";
        System.out.println(submenu);
    }

    /**
     * This method prints all the folders that are currently in the mailbox in a neat table.
     */
    public static void displayMailbox() {
        String printedMailbox = String.format("%s\n%s\n", "Mailbox:", "--------");

        for(int i = 0; i < mailbox.folders.size(); i++) {
            printedMailbox += mailbox.folders.get(i).getName() + "\n";
        }

        System.out.println(printedMailbox);
    }

    /**
     * This method runs the submenu actions as a supporting method whenever a folder is entered.
     * @param folder
     */
    public static void subMenuActions(Folder folder) {
        do {
            try {
                System.out.println(folder);
                displaySubmenu();

                System.out.print("Select a submenu option: ");
                subMenuOption = input.next().toLowerCase();
                System.out.println();

                switch (subMenuOption) {
                    case "m":
                        System.out.print("Enter the index of the email to move: ");
                        int moveEmailIndex = input.nextInt();
                        System.out.println();

                        displayMailbox();
                        Email tempMoveEmail = folder.getEmails().get(moveEmailIndex - 1);

                        System.out.print("Select a folder to move \"" + tempMoveEmail.getSubject() + "\" to: ");
                        String moveFolderName = inputString.nextLine();
                        System.out.println();

                        boolean flag = false;
                        for(int i = 0; i < mailbox.folders.size(); i++) {
                            if(mailbox.folders.get(i).getName().equalsIgnoreCase(moveFolderName)) {
                                mailbox.moveEmail(folder.removeEmail(moveEmailIndex - 1), mailbox.folders.get(i));
                                System.out.println("\"" + tempMoveEmail.getSubject() + "\" successfully moved to \"" + moveFolderName + "\" folder.\n");
                                flag = true;
                                break;
                            }
                        }
                        if(!flag) {
                            System.out.println("The email was not moved because the folder entered could not be found.\n");
                        }
                        break;
                    case "d":
                        System.out.print("Enter the index of the email you want to remove: ");
                        int removeEmailIndex = input.nextInt();
                        System.out.println();

                        Email tempRemoveEmail = folder.removeEmail(removeEmailIndex - 1);

                        mailbox.deleteEmail(tempRemoveEmail);
                        System.out.println("\"" + tempRemoveEmail.getSubject() + "\" successfully moved to the trash.\n");
                        break;
                    case "v":
                        System.out.print("Enter email index: ");
                        int viewEmailIndex = input.nextInt();
                        System.out.println();

                        System.out.println(folder.getEmails().get(viewEmailIndex - 1).viewEmail());
                        break;
                    case "sa":
                        folder.sortBySubjectAscending();
                        break;
                    case "sd":
                        folder.sortBySubjectDescending();
                        break;
                    case "da":
                        folder.sortByDateAscending();
                        break;
                    case "dd":
                        folder.sortByDateDescending();
                        break;
                    case "r":
                        System.out.println("Returning to mailbox.\n"); //Program terminating normally...
                        break;
                    default:
                        System.out.println("You did not select a valid menu option. Please try again.\n");
                        break; //Not needed after the default but recommended.
                }
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("There was a problem with the index of an array. This was likely because the index entered is invalid,\n");
            } catch(IndexOutOfBoundsException e) {
                System.out.println("There was an index out of bounds exception. This was likely because the index entered is invalid,\n");
            } catch(NumberFormatException e) {
                System.out.println("It seems that there was a number format exception.\n");
            } catch(InputMismatchException e) {
                System.out.println("Please enter an argument of the right type.\n");
                input.next();
            } catch(NoSuchElementException e) {
                System.out.println("There is a no such element exception occurring.\n");
            } catch(IllegalArgumentException e) {
                System.out.println("Please enter a position that is within the valid range next time. This was likely because the index entered is invalid,\n");
            } catch(NullPointerException e) {
                System.out.println("There seems to be a null-pointer exception occurring.\n");
            } catch(Exception e) {
                System.out.println("There seems to be an error that was not entirely accounted for.\n");
            }
        }
        while (subMenuOption.compareToIgnoreCase("r") != 0);
    }
}
