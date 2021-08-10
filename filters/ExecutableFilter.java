package filters;

import java.io.File;

/**
 * Files filter
 * Filter rule: The file has execution permission
 */
class ExecutableFilter extends Filter {

    @Override
    boolean condition(File file){
        return ParameterChecker.YesNoBox(parameters[0],file.canExecute());
    }

    @Override
    boolean checkArguments(String[] args) {
        return (super.checkArguments(args) &&
                ParameterChecker.binaricParameterCheck(args[0]));
    }
}
