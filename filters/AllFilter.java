package filters;
import java.io.File;

/**
 * Files filter
 * Filter rule: returns all files
 */
public class AllFilter extends Filter {

    @Override
    boolean condition(File file){
        return true;
    }

}