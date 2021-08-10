package filters;

import filesprocessing.TypeOneErrorException;

/**
 * This exception is thrown when defective parameters are detected in the commands file.
 * It is considered as Type I error.
 */
public class BadFilterParametersException extends TypeOneErrorException {
    BadFilterParametersException(Integer lineNumber){
        super(lineNumber);
    }

}
