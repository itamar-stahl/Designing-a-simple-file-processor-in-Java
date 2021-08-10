package filters;

/** Filters Factory class.
 */
public class FilterFactory {

    public enum FilterType {
        GREATER_THAN{
            Filter getFilterInstance() {return new GreaterThanFilter();}},
        BETWEEN{
            Filter getFilterInstance() {return new BetweenFilter();}},
        SMALLER_THAN{
            Filter getFilterInstance() {return new SmallerThenFilter();}},
        FILE{
            Filter getFilterInstance() {return new FileFilter();}},
        CONTAINS{
            Filter getFilterInstance() {return new ContainsFilter();}},
        PREFIX{
            Filter getFilterInstance() {return new PrefixFilter();}},
        SUFFIX{
            Filter getFilterInstance() {return new SuffixFilter();}},
        WRITABLE{
            Filter getFilterInstance() {return new WritableFilter();}},
        EXECUTABLE{
            Filter getFilterInstance() {return new ExecutableFilter();}},
        HIDDEN{
            Filter getFilterInstance() {return new HiddenFilter();}},
        ALL{
            Filter getFilterInstance() {return new AllFilter();} };

        abstract Filter getFilterInstance();
    }

    public static Filter getFilter(String filterName, Integer lineNumber) throws BadFilterNameException{
        Filter selectedFilter = null;
        for (FilterFactory.FilterType filterHolder : FilterFactory.FilterType.values()){
            if (filterHolder.toString().toLowerCase().equals(filterName)){
                selectedFilter = filterHolder.getFilterInstance();
                break;
            }
        }
        if (selectedFilter == null) throw new BadFilterNameException(lineNumber);
        return selectedFilter;
    }


}



