package Homework5;

import java.util.Comparator;

public class SubjectDescendingComparator implements Comparator<Email> {
    public int compare(Email leftSide, Email rightSide) {
        String o1 = leftSide.getSubject();
        String o2 = rightSide.getSubject();

        return o2.compareToIgnoreCase(o1);
    }
}
