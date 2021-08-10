package filters;

import filesprocessing.TypeOneErrorException;

/**
 * This exception is thrown when a defective filter name id detected in the commands file.
 * It is considered as Type I error.
 */
public class BadFilterNameException extends TypeOneErrorException {

    BadFilterNameException(Integer lineNumber){
        super(lineNumber);
    }

}
