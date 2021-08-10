package sorting;
import java.io.File;
import java.util.ArrayList;

/**
 * Sorter Interface
 */
public interface SortStrategy {

   /**
    Installs the filter parameters.
    */
   void setSortParameters(String[] args, Integer lineNumber) throws BadOrderParametersException;

   /**
    * Sorts a files list
    * @param filesList - the list to sort
    * @return String[] - sorted list
    */
   String[] sortList(ArrayList<File> filesList);

}
