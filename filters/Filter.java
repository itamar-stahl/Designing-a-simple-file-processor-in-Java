package filters;

import java.io.File;
import java.util.ArrayList;

/**
 * Files filter.
 * For a list of files:
 * Returns only the files that fulfil some condition.
 */
public abstract class Filter {

    // If a NOT suffix appears on a line, it inverts the filter rule
    // For example: NOT hidden means filtering OUT hidden files.
    protected boolean notSuffix = false;
    protected String[] parameters;

    static final String NEGATIVE_FILTER_KEYWORD = "NOT";
    static final int NUM_OF_OPTIONAL_ARGS = 1;
    static final int DEFAULT_NUM_OF_ARGS = 1;

    /*This method inverts boolean expression iff notSuffix == true*/
    protected boolean inversionBox(boolean booleanExpression){
        return notSuffix != booleanExpression;
    }

    /**
     Installs the filter parameters
     */
    public void setFilterParameters(String[] args, Integer lineNumber) throws BadFilterParametersException {
        if (!checkArguments(args)) throw new BadFilterParametersException(lineNumber);
        if ((args.length != 0) && (args[args.length-1].equals(NEGATIVE_FILTER_KEYWORD))) notSuffix = true;
        parameters = args;
    }

    /*
    The condition the file has to pass
     */
    abstract boolean condition(File file);

    protected int numOfVariables(){
        return DEFAULT_NUM_OF_ARGS;
    }

    /*
     Checks the validity of the arguments for the filter parameters.
     */
    boolean checkArguments(String[] args)
    {
        return parameterNumberTest(args,this.numOfVariables());
    }

      /*
    Checks the validity of parameters number
    */
    protected boolean parameterNumberTest(String[] args, int numOfArgs){
        return (args.length == numOfArgs) || ((args.length == numOfArgs + NUM_OF_OPTIONAL_ARGS) &&
                (args[args.length-1].equals(Filter.NEGATIVE_FILTER_KEYWORD)));
     }

    /**
     * main method. Iterate over the given file list
     * and return filtered list.
     * @param filesList - the list to filter
     * @return ArrayList<File> - filtered list
     */
    public ArrayList<File> filter(File[] filesList){
       ArrayList<File> filteredList = new ArrayList<File>();
       for (File file : filesList){
           if (inversionBox(condition(file))) filteredList.add(file);
       }
      return filteredList;
    }

}
