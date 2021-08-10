package sorting;

import java.io.File;
import java.util.Comparator;


/**
 * Comparision rule: file size (smallest is first).
 * In case of equality compares names
 */
public class SizeComparator implements Comparator<File> {

    @Override
    public int compare(File f1, File f2) {
        double sizeF1 = f1.length();
        double sizeF2 = f2.length();
        return (sizeF1 != sizeF2) ? sizeCompare(sizeF1, sizeF2) :
                f1.getName().compareTo(f2.getName());

    }

    private int sizeCompare(double sizeF1, double sizeF2){
        return (sizeF1 < sizeF2) ? -1 : 1;
    }
}
