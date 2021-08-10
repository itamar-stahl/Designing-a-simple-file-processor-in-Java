package filters;

import java.io.File;


/**
 * Files filter
 * Filter rule: The file name ends with the given string.
 */
class SuffixFilter extends Filter {
    
    @Override
    boolean condition(File file){
        return file.getName().endsWith(parameters[0]);
    }

}
