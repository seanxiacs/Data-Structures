package Homework5;

/**
 * Email contains information about the email.
 * @author Sean Xia, SBU ID 113181409, sean.xia@stonybrook.edu, HW#4, CSE214, R30, TAs: Charles Clark, Amogh Joshi, Sharfuddin Mohammed, Vinayak Shenoy.
 */

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Email implements Serializable {
    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String body;
    private GregorianCalendar timestamp;

    /**
     * Default constructor of the Email class.
     */
    public Email() {

    }

    /**
     * Overloaded constructor that sets all the information about the Email.
     * @param to
     * @param cc
     * @param bcc
     * @param subject
     * @param body
     * @param timestamp
     */
    public Email(String to, String cc, String bcc, String subject, String body, GregorianCalendar timestamp) {
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.body = body;
        this.timestamp = timestamp;
    }

    /**
     * This method sets the to.
     * @param to
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * This method sets the cc.
     * @param cc
     */
    public void setCC(String cc) {
        this.cc = cc;
    }

    /**
     * This method sets the bcc.
     * @param bcc
     */
    public void setBCC(String bcc) {
        this.bcc = bcc;
    }

    /**
     * This method sets the subject.
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * This method sets the body.
     * @param body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * This method sets the timestamp.
     * @param timestamp
     */
    public void setTimestamp(GregorianCalendar timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * This method gets the to.
     * @return to
     */
    public String getTo() {
        return to;
    }

    /**
     * This method gets the cc.
     * @return cc
     */
    public String getCC() {
        return cc;
    }

    /**
     * This method gets the bcc.
     * @return bcc
     */
    public String getBCC() {
        return bcc;
    }

    /**
     * This method gets the subject.
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * This method gets the body.
     * @return body
     */
    public String getBody() {
        return body;
    }

    /**
     * This method gets the timestamp.
     * @return timestamp
     */
    public GregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     * This method returns a string representation of the email.
     * @return returned
     */
    public String viewEmail() {
        String returned = "";
        returned += "To: " + to + "\n";
        returned += "CC: " + cc + "\n";
        returned += "BCC: " + bcc + "\n";
        returned += "Subject: " + subject + "\n";
        returned += "Body: \n" + body + "\n";

        return returned;
    }

    /**
     * This method returns a shorter string representation of the email that is used for creating the table of emails in the folders.
     * @return information
     */
    public String toString() {
        String information = "";
        Date timed = timestamp.getTime();
        DateFormat formatter = new SimpleDateFormat("hh:mmaa MM/dd/yyyy");
        String formatted = formatter.format(timed);
        information += String.format("%-20s%s", formatted, "|"); //timestamp.getTime() used to be inside
        information += String.format("%-20s", subject);

        return information;
    }
}
