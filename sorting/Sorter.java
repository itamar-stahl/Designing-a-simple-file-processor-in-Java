package sorting;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * Sorts files list according to a given comparator (Abs by default)
 */
public class Sorter implements SortStrategy {

    private Comparator<File> comparator;
    private boolean reverseDirection = false;
    private static final String REVERSE_ORDER_KEYWORD = "REVERSE";

    /**
     * default constructor
     */
    public Sorter() {
        this.comparator = new AbsComparator();
    }

    /**
     * modified constructor
     */
    Sorter(Comparator<File> comparatorInput) {
        this.comparator = comparatorInput;
    }

    @Override
    public void setSortParameters(String[] args, Integer lineNumber) throws BadOrderParametersException {
        if (!checkArgs(args)) {
            throw new BadOrderParametersException(lineNumber);
        }
        if ((args.length != 0)&&(args[args.length-1].equals(REVERSE_ORDER_KEYWORD))) reverseDirection = true;

    }

    public String[] sortList(ArrayList<File> filesList){
        ArrayList<File> sortedList = quickSort(filesList, this.comparator);
        if (reverseDirection) Collections.reverse(sortedList);
        String[] finalArray = new String[sortedList.size()];
        for (int i=0;i<sortedList.size();i++){
            finalArray[i] = sortedList.get(i).getName();
        }
        return finalArray;

    }

    private boolean checkArgs(String[] args){
        return parameterNumberTest(args,0,1);
    }

    private static boolean parameterNumberTest(String[] args, int lower, int upper) {
        return (args.length == lower) || ((args.length == upper) &&
                (args[args.length - 1].equals(REVERSE_ORDER_KEYWORD)));
    }

    /**
     * Recursive implementation of QuickSort (For educational purposes).
         * @param list - The list to sort
         * @param comparator - A sorting rule
         * @returns new sorted list
         */
    <T> ArrayList<T> quickSort(ArrayList<T> list, Comparator<T> comparator) {

        ArrayList<T> larger = new ArrayList<T>();
        ArrayList<T> smaller = new ArrayList<T>();

        if (list.size() <= 1) return list; //stop condition

        // getting a pivot element
        Random random = new Random();
        int pivotIndex = random.nextInt(list.size() - 1);
        T pivot = list.get(pivotIndex);
        list.remove(pivotIndex);

        // Puts smaller elements than the pivot on the left of it, larger on its right
        ArrayList<T> equalElement = new ArrayList<T>();
        for (T elem : list) {
            int comparisonResult = comparator.compare(elem, pivot);
            if (comparisonResult < 0) {
                smaller.add(elem);
            } else if (comparisonResult > 0) {
                larger.add(elem);
            } else {
                equalElement.add(elem); 
            }
        }
        smaller.addAll(equalElement);

        // Sort the left list and the right list
        ArrayList<T> downSmaller = quickSort(smaller, comparator);
        ArrayList<T> downLarger = quickSort(larger, comparator);

        //concatenating block
        downSmaller.add(pivot);
        downSmaller.addAll(downLarger);
        return downSmaller;
    }

}


