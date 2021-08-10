package filesprocessing;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/** Reads the commands file and convert it to an array of Section[] Objects which
 * contain execution data. 
 * See the README file for more information about the commands file structure.
 */
class Interpreter {
    private enum InterpreterLocation {ROW1,ROW2,ROW3,ROW4_OR_NEW_START}
    private static final String BAD_FORMAT_IN_FILTER_SUB_SECTION_MSG = "FILTER sub-section declaration is" +
            " missing/misspelled in line ";
    private static final String BAD_FORMAT_IN_ODDER_SUB_SECTION_MSG = "ORDER sub-section declaration is" +
            " missing/misspelled in line ";
    private static final String BAD_FORMAT_IN_LAST_SECTION_MSG = "Last section is chopped";
    private static final String FILTER_SECTION_START_KEYWORD = "FILTER";
    private static final String SORTING_SECTION_START_KEYWORD = "ORDER";
    private static final String SEPARATOR = "#";
    static final String UNASSIGNED_NAME_MARKER = "@";
    private static final String[] UNASSIGNED_ARGS_MARKER = {};
    private static final int UNASSIGNED_LINE_NUMBER_MARKER = -1;

    private String[] commands; // Holds all the commands lines after reading from file.
    private ArrayList<Section> sections = new ArrayList<Section>();
    private InterpreterLocation interpreterMode; // cursor that indicates in which line (1-4) within a section
                                                // the interpreter is located
    
    // Temporal variables for the current read section 
    private String filterName;
    private String orderName;
    private String[] filterArgs;
    private String[] orderArgs;
    private int filerCommandLineNumber;
    private int orderCommandLineNumber;

    /**Constructor
     * @param commandFile - path of the command file.
     * */
    Interpreter(String commandFile) throws IOProblemException {
        commands = fileToCommands(commandFile);
        interpreterMode = InterpreterLocation.ROW1;
        resetSectionVariable();
    }
    /*Resets the section variable before each reading of new section of commands*/
    private void resetSectionVariable(){
        filterName = UNASSIGNED_NAME_MARKER;
        orderName = UNASSIGNED_NAME_MARKER;
        filterArgs = UNASSIGNED_ARGS_MARKER;
        orderArgs = UNASSIGNED_ARGS_MARKER;
        filerCommandLineNumber = UNASSIGNED_LINE_NUMBER_MARKER;
        orderCommandLineNumber = UNASSIGNED_LINE_NUMBER_MARKER;
    }

    /* Main function. It iterates on the commands array and put them in a
     * Section[] array. 
     */
    Section[] interpret() throws BadCommandFileFormatException {
        for (int line=1; line <= commands.length; line++) {
            switch (interpreterMode) {
                case ROW1:
                    formatValidityTest(commands[line-1], line);
                    interpreterMode = InterpreterLocation.ROW2;
                    continue;
                case ROW2:
                    filerCommandLineNumber = line;
                    filterLineDecoder(commands[line-1]);
                    interpreterMode = InterpreterLocation.ROW3;
                    continue;
                case ROW3:
                    formatValidityTest(commands[line-1], line);
                    interpreterMode = InterpreterLocation.ROW4_OR_NEW_START;
                    continue;
                case ROW4_OR_NEW_START:
                    if (!FILTER_SECTION_START_KEYWORD.equals(commands[line-1])) {
                        orderCommandLineNumber = line;
                        orderLineDecoder(commands[line-1]);
                        interpreterMode = InterpreterLocation.ROW1;
                    } else {
                        interpreterMode = InterpreterLocation.ROW2;
                    }
                    sections.add(new Section(filterName, orderName, filterArgs, orderArgs,
                           filerCommandLineNumber, orderCommandLineNumber));
                    resetSectionVariable();
            }
        }
        if (!((interpreterMode == InterpreterLocation.ROW1) ||
        (interpreterMode == InterpreterLocation.ROW4_OR_NEW_START))) throw
                new BadCommandFileFormatException(BAD_FORMAT_IN_LAST_SECTION_MSG);
        if (interpreterMode == InterpreterLocation.ROW4_OR_NEW_START){
            sections.add(new Section(filterName, orderName, filterArgs, orderArgs,
                    filerCommandLineNumber, orderCommandLineNumber));
        }
        Section[] result = new Section[sections.size()];
        sections.toArray(result);
        return result;
        
    }

    /*
     * Inserts values from a filter command line into a variable.
     * @param line - command line
     */
    private void filterLineDecoder(String line){
        String[] splitLine = line.split(SEPARATOR);
        filterName = splitLine[0];

        filterArgs = Arrays.copyOfRange(splitLine,1, splitLine.length);

    }

    /*
     * Insert values from a sorting order command line into a variable.
     * @param line - command line
     */
    private void orderLineDecoder(String line){
        String[] splitLine = line.split(SEPARATOR);
        orderName = splitLine[0];

        orderArgs = Arrays.copyOfRange(splitLine,1, splitLine.length);

    }


    /*
     * Reads a text file of commands (such that each line contains a single word),
     * and returns a string array of its lines.
     *  @param fileName: Text file
     *  @return String[] Array:  file's content (returns null if the IOException occurred).
     */

    private static String[] fileToCommands(String fileName) throws IOProblemException {
        // A list to hold the file's content
        List<String> fileContent = new ArrayList<String>();

        // Reader object for reading the file
        BufferedReader reader = null;

        try {
            // Open a reader
            reader = new BufferedReader(new FileReader(fileName));

            // Read the first line
            String line = reader.readLine();

            // Go over the rest of the file
            while (line != null) {

                // Add the line to the list
                fileContent.add(line);

                // Read the next line
                line = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            throw new IOProblemException(fileName, true);
        } catch (IOException e) {
            throw new IOProblemException();
        } finally {
            // Try to close the file
            try {
                if(reader != null)
                    reader.close();
                else
                    return null;
            } catch (IOException e) {
                throw new IOProblemException(fileName);
            }

        }

        // Converts the list to an array return it
        String[] result = new String[fileContent.size()];
        fileContent.toArray(result);
        return result;
    }



    /* This function detects Type 2 errors as a result of bad command file format */
    private void formatValidityTest(String commandLine,
                                       Integer lineNumber) throws BadCommandFileFormatException {
        switch (interpreterMode){
            case ROW1:
                if (FILTER_SECTION_START_KEYWORD.equals(commandLine)){
                    return;
                } else {
                    throw new BadCommandFileFormatException(BAD_FORMAT_IN_FILTER_SUB_SECTION_MSG
                            +lineNumber.toString());
                }

            case ROW3:
                if (SORTING_SECTION_START_KEYWORD.equals(commandLine)) {
                    break;
                } else {
                    throw new BadCommandFileFormatException(BAD_FORMAT_IN_ODDER_SUB_SECTION_MSG
                            +lineNumber.toString());
                }

        }

    }
}

