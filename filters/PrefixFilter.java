package filters;

import java.io.File;

/**
 * Files filter
 * Filter rule: The file name starts with the given string.
 */
class PrefixFilter extends Filter {

    @Override
    boolean condition(File file){
        return file.getName().startsWith(parameters[0]);
    }

}
