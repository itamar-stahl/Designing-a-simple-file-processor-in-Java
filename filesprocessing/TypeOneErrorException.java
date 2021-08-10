package filesprocessing;

/**
 * The behavior of the program when such error occurs is the following:
 * 1) A warning would be printed to System.err.
 * 2) Execution with the default settings.
 */
public class TypeOneErrorException extends Exception {
    public static final String TYPE_I_GENERAL_MSG = "Warning in line ";

    public TypeOneErrorException(Integer lineNumber){
        super(TYPE_I_GENERAL_MSG + lineNumber.toString());
    }

}