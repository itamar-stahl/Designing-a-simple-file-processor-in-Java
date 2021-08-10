package filters;


/**
 * Contains static methods that perform checks on arguments input,
 * that several filter classes use.
 */
public class ParameterChecker {

    /*
     List of the valid parameters of binaric filters
     */
    private enum BinaricParameter {YES, NO}

    /*
    Checks the validity of parameters number
    */
    public static boolean parameterNumberTest(String[] args, int num){
        return args.length == num;
    }

    /*
    Used by binaric filters - set the booleanExpression value according to
    the YES/NO parameter
    */
    static boolean YesNoBox(String binaricParameter, boolean booleanExpression){
        if (BinaricParameter.YES.toString().equals(binaricParameter)) {
            return booleanExpression;
        }
       return (!booleanExpression);
    }

  

    /*
     Checks the validity of binaric filters
     */
    static boolean binaricParameterCheck(String arg){
       if ((arg.equals(BinaricParameter.YES.toString())) ||
               (arg.equals(BinaricParameter.NO.toString()))) return true;
       return false;
    }

    /*
     Return true/false
     */
    static boolean isNonNegative(double arg){
        return arg >= 0;
    }

    /*
     Size converter
     */
    static double kiloBytesToBytes(double sizeInKiloBytes){return 1024*sizeInKiloBytes;}
}
