package filters;

import java.io.File;
/**
 * Files filter
 * Filter rule: The file size file is x<= fileSize <=y
 */
class BetweenFilter extends Filter {
    static final int NUM_OF_ARGS = 2;

    @Override
    boolean condition(File file){
        double fileSize = file.length();
        return (ParameterChecker.kiloBytesToBytes(Double.parseDouble(parameters[0])) <= fileSize) &&
                (fileSize <= ParameterChecker.kiloBytesToBytes(Double.parseDouble(parameters[1])));
    }

    @Override
    protected int numOfVariables(){
        return NUM_OF_ARGS;
    }

    @Override
    boolean checkArguments(String[] args) {
        return  (super.checkArguments(args) &&
                ParameterChecker.isNonNegative(Double.parseDouble(args[0]))
                && ParameterChecker.isNonNegative(Double.parseDouble(args[1])) &&
                (ParameterChecker.isNonNegative(Double.parseDouble(args[1])
                        - Double.parseDouble(args[0]))));
    }
}
