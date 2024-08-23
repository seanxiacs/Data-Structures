package Homework5;

/**
 * Folder creates an arraylist of all the emails in a certain folder of emails. It also contains the name of the folder and the current sorting method of the folder.
 * @author Sean Xia, SBU ID 113181409, sean.xia@stonybrook.edu, HW#4, CSE214, R30, TAs: Charles Clark, Amogh Joshi, Sharfuddin Mohammed, Vinayak Shenoy.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Folder implements Serializable {
    private ArrayList<Email> emails = new ArrayList<>();
    private String name;
    private String currentSortingMethod = "dateDescending";

    /**
     * Default constructor of the Folder class.
     */
    public Folder() {

    }

    /**
     * Overloaded constructor that sets the name of the folder.
     * @param name
     */
    public Folder(String name) {
        this.name = name;
    }

    /**
     * Overloaded constructor that sets all the information about the Folder.
     * @param emails
     * @param name
     * @param currentSortingMethod
     */
    public Folder(ArrayList<Email> emails, String name, String currentSortingMethod) {
        this.emails = emails;
        this.name = name;
        this.currentSortingMethod = currentSortingMethod;
    }

    /**
     * This method sets the emails.
     * @param emails
     */
    public void setEmails(ArrayList<Email> emails) {
        this.emails = emails;
    }

    /**
     * This method sets the name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method sets the current sorting method.
     * @param currentSortingMethod
     */
    public void setCurrentSortingMethod(String currentSortingMethod) {
        this.currentSortingMethod = currentSortingMethod;
    }

    /**
     * This method returns the emails.
     * @return emails
     */
    public ArrayList<Email> getEmails() {
        return emails;
    }

    /**
     * This method gets the name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns the current sorting method.
     * @return currentSortingMethod
     */
    public String getCurrentSortingMethod() {
        return currentSortingMethod;
    }

    /**
     * This method adds the email to the end of the arraylist and sorts after the email is added.
     * @param email
     */
    public void addEmail(Email email) {
        emails.add(email);

        if(currentSortingMethod.equalsIgnoreCase("subjectAscending")) {
            Collections.sort(emails, new SubjectAscendingComparator());
        }
        if(currentSortingMethod.equalsIgnoreCase("subjectDescending")) {
            Collections.sort(emails, new SubjectDescendingComparator());
        }
        if(currentSortingMethod.equalsIgnoreCase("dateAscending")) {
            Collections.sort(emails, new DateAscendingComparator());
        }
        if(currentSortingMethod.equalsIgnoreCase("dateDescending")) {
            Collections.sort(emails, new DateDescendingComparator());
        }
    }

    /**
     * This method removes the email from the folder and
     * @param index
     * @return tempEmail
     */
    public Email removeEmail(int index) {
        Email tempEmail = null;
        tempEmail = emails.remove(index);
        return tempEmail;
    }

    /**
     * This method sets the sorting method to be subject ascending.
     */
    public void sortBySubjectAscending() {
        this.currentSortingMethod = "subjectAscending";
        Collections.sort(emails, new SubjectAscendingComparator());
    }

    /**
     * This method sets the sorting method to be subject descending.
     */
    public void sortBySubjectDescending() {
        this.currentSortingMethod = "subjectDescending";
        Collections.sort(emails, new SubjectDescendingComparator());
    }

    /**
     * This method sets the sorting method to be date ascending.
     */
    public void sortByDateAscending() {
        this.currentSortingMethod = "dateAscending";
        Collections.sort(emails, new DateAscendingComparator());
    }

    /**
     * This method sets the sorting method to be date descending.
     */
    public void sortByDateDescending() {
        this.currentSortingMethod = "dateDescending";
        Collections.sort(emails, new DateDescendingComparator());
    }

    /**
     * This method returns a string representation of the folder in a table.
     * @return table
     */
    public String toString() {
        String table = getName() + "\n";

        table += String.format("%-7s%s%-20s%s%-30s\n", "Index", "|", "Time", "|", "Subject");

        table += new String(new char[58]).replace("\0", "-");
        table += "\n";

        for(int i = 0; i < emails.size(); i++) {
            table += String.format("%-7d%s%s\n", i + 1, "|", emails.get(i));
        }

        return table;
    }
}
