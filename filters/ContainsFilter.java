package filters;

import java.io.File;

/**
 * Files filter
 * Filter rule: The file name contains the given string.
 */
public class ContainsFilter extends Filter {

    @Override
    boolean condition(File file){
        return file.getName().contains(parameters[0]);
    }

  
}
