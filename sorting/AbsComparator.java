package sorting;

import java.io.File;
import java.util.Comparator;

/**
 * Comparision rule: the absolute name (a is first)
 */
public class AbsComparator implements Comparator<File> {
    @Override
    public int compare(File o1, File o2) {
        return o1.getName().compareTo(o2.getName());
    }

}
