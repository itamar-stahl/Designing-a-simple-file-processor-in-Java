package filesprocessing;

import filters.BadFilterNameException;
import filters.BadFilterParametersException;
import filters.Filter;
import filters.FilterFactory;
import sorting.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class get array of Sections which contains filtering and
 * sorting orders for the File[] file list that it receives.
 */
class SectionProcessor {

    /*
     Assigns the proper filter and filter parameters and returns a Filter Object
     */
    private static Filter assignFilter(String filterName, String[] filterArgs, Integer filterCommandLine){
        Filter filter;
        try {
            filter = FilterFactory.getFilter(filterName,filterCommandLine);
            filter.setFilterParameters(filterArgs,filterCommandLine);
        } catch (BadFilterNameException | BadFilterParametersException e){
            System.err.println(e.getLocalizedMessage());
            filter = new filters.AllFilter();
        }
        return filter;
    }

    /*
     Assigns the proper sorter and sorter parameters and return SortStrategy Object
     */
    private static SortStrategy assignSorter(String sorterName, String[] sortArgs, Integer sortCommandLine){
        SortStrategy sorter;
        try {
            sorter = SortStrategyFactory.getSorter(sorterName, sortCommandLine);
            sorter.setSortParameters(sortArgs, sortCommandLine);
        } catch (BadOrderParametersException | BadOrderNameException e){
            if (!sorterName.equals(Interpreter.UNASSIGNED_NAME_MARKER)) {
                System.err.println(e.getLocalizedMessage());
            }
            sorter = new Sorter();
        }
        return sorter;
    }

    /*
     helper function for printing output to the screen.
     */
    private static void print(ArrayList<String> output){
        for (String registration : output){
            System.out.println(registration);
        }
    }

    /**
      Main method - iterates over the Section[] array
      and execute the filtering and sorting commands.
      @param sections - array of Section object
     */
    static void execute(Section[] sections, File[] fileList){
        Filter filter;
        SortStrategy sorter;
        ArrayList<String> output = new ArrayList<String>();

        for (Section section : sections){
          filter = assignFilter(section.getFilterName(), section.getFilterArgs(),
                  section.getFilerCommandLineNumber());
          sorter = assignSorter(section.getOrderName(), section.getOrderArgs(),
                  section.getOrderCommandLineNumber());
          ArrayList<File> filteredList = filter.filter(fileList);
          String[] sortedList = sorter.sortList(filteredList);
          output.addAll(Arrays.asList(sortedList));
        }
        print(output);
    }
}
