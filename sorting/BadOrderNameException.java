package sorting;

import filesprocessing.TypeOneErrorException;

/**
 * This exception is thrown when a defective order name is detected in the commands file.
 * It is considered as Type I error.
 */
public class BadOrderNameException extends TypeOneErrorException {
    BadOrderNameException(Integer lineNumber){
        super(lineNumber);
    }


}
