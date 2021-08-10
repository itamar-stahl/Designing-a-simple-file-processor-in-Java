package filters;

import java.io.File;

/**
 * Files filter
 * Filter rule: fileSize > x
 */
class GreaterThanFilter extends Filter {

    @Override
    boolean condition(File file){
        return file.length() > ParameterChecker.kiloBytesToBytes(Double.parseDouble(parameters[0]));
    }

    @Override
    boolean checkArguments(String[] args) {
        return  (super.checkArguments(args) &&
                ParameterChecker.isNonNegative(Double.parseDouble(args[0])));
    }
}
