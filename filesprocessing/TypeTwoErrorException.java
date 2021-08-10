package filesprocessing;

/**
 *  The behavior of the program when such error occurs is the following:
 * 1) A error massage would be printed to System.err.
 * 2) The program would be terminated.
 */
public class TypeTwoErrorException extends Exception {
    static final String TYPE_II_GENERAL_MSG = "ERROR: ";

    public TypeTwoErrorException(String errorMsg){
        super(TYPE_II_GENERAL_MSG + errorMsg);
    }

}