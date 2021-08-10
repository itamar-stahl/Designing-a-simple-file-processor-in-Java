package filesprocessing;

/**
 * This exception is thrown when a defective file format is detected in commands file.
 * It is considered as Type II error
 */
public class BadCommandFileFormatException extends TypeTwoErrorException {

    BadCommandFileFormatException(String errorMsg) {
        super(errorMsg);
    }

}
