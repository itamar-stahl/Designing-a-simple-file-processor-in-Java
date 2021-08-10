package filters;

import java.io.File;

/**
 * Files filter
 * Filter rule: The file has write permission
 */
class WritableFilter extends Filter {

    boolean condition(File file){
        return ParameterChecker.YesNoBox(parameters[0],file.canWrite());
    }

    boolean checkArguments(String[] args) {
        return (super.checkArguments(args) && ParameterChecker.binaricParameterCheck(args[0]));
    }
}
