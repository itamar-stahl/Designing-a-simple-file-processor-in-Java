package filters;

import java.io.File;

/**
 * Files filter
 * Filter rule: The file is hidden.
 */
class HiddenFilter extends Filter {
    
    @Override
    boolean condition(File file){
        return ParameterChecker.YesNoBox(parameters[0],file.isHidden());
    }

    @Override
    boolean checkArguments(String[] args) {
        return (super.checkArguments(args) &&
                ParameterChecker.binaricParameterCheck(args[0]));
    }
}
