package Homework5;

import java.util.Comparator;
import java.util.GregorianCalendar;

public class DateDescendingComparator implements Comparator<Email> {
    public int compare(Email leftSide, Email rightSide) {
        GregorianCalendar o1 = leftSide.getTimestamp();
        GregorianCalendar o2 = rightSide.getTimestamp();

        return o2.compareTo(o1);
    }
}
