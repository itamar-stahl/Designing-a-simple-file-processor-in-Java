package filesprocessing;

/**
 * This exception is thrown when an IO problem occurs while accessing to
 * the command file.
 * It is considered as Type II error
 */
public class IOProblemException extends TypeTwoErrorException {
    public static final String NOT_FOUND_P_1 = "The file: ";
    public static final String NOT_FOUND_P_2 = " is not found." ;
    public static final String GENERAL = "An IO error occurred.";
    public static final String CLOSE_PROBLEM = "Could not close the file: ";

    IOProblemException(){
        super(GENERAL);
    }

    /**
     * Called when the program fail to close a file
     */
    IOProblemException(String fileName){
        super(CLOSE_PROBLEM + fileName + ".");
    }

     /**
     * Called when the program fail to find a file
     */
    IOProblemException(String fileName, boolean notFound){
        super(NOT_FOUND_P_1 + fileName + NOT_FOUND_P_2);
    }

}

