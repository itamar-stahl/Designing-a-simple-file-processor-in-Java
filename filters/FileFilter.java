package filters;

import java.io.File;
/**
 * Files filter
 * Filter rule: The file has the given name
 */
public class FileFilter extends Filter {

    @Override
    boolean condition(File file){
        return file.getName().equals(parameters[0]);
    }

}
