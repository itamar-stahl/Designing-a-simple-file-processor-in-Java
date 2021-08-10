package filesprocessing;

import java.io.File;
import java.io.FileFilter;


/**
 * Main class. Manages other classes and handles errors.
 */
public class DirectoryProcessor {

    public static final int NUM_OF_ARGS = 2;
    private static final String BAD_COMMAND_LINE_ARGUMENTS = "Invalid command line arguments.";
    private static final String UNKNOWN_ERROR_COMMAND_LINE_ARGUMENTS = "Unknown error while testing" +
            " command line arguments. ";
    private static final String UNKNOWN_ERROR_WHILE_ACCESSING_FILES = "Unknown error while accessing files in " +
            "the source directory.";


    /* Gets Source Directory path and returns Arrays of files
       which stored inside it.
     */
    private static File[] filesMiner(String path) {
        try {
            File directoryPath = new File(path);
            FileFilter onlyFile = new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.isFile();
                }
            }; // filter directories
            return directoryPath.listFiles(onlyFile);
        } catch (Exception e) {
            System.err.println(UNKNOWN_ERROR_WHILE_ACCESSING_FILES);
            return null;
        }

    }

    /*
     Test the validity of the command line arguments
     */
    private static boolean argsAreValid(String[] args) {
        try {
            if (args.length != NUM_OF_ARGS) {
                System.err.println(TypeTwoErrorException.TYPE_II_GENERAL_MSG +
                                                         BAD_COMMAND_LINE_ARGUMENTS);
                return false;
            }
            File sourceDirectory = new File(args[0]);
            File commandFile = new File(args[1]);
            if ((!sourceDirectory.isDirectory()) || (!commandFile.isFile())) {
                System.err.println(TypeTwoErrorException.TYPE_II_GENERAL_MSG +
                                                         BAD_COMMAND_LINE_ARGUMENTS);
                return false;
            }
        } catch (Exception e) {
            System.err.println(TypeTwoErrorException.TYPE_II_GENERAL_MSG +
                                             UNKNOWN_ERROR_COMMAND_LINE_ARGUMENTS);
            return false;
        }
        return true;
    }

    /**
     * Main method.
     * @param args - The arguments from the command line
     */
    public static void main(String[] args) {
        if (!argsAreValid(args)) return;
        File[] sourceDirectoryFiles = filesMiner(args[0]);
        if ((sourceDirectoryFiles != null) && (sourceDirectoryFiles.length != 0)) {
            try {
                Interpreter commandsInterpreter = new Interpreter(args[1]);
                Section[] sectionsForExecution = commandsInterpreter.interpret();
                SectionProcessor.execute(sectionsForExecution, sourceDirectoryFiles);
            } catch (IOProblemException | BadCommandFileFormatException e) {
                System.err.println(e.getLocalizedMessage());
            }

        }
    }
}
