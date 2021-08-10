package sorting;

/** 
 * A factory class. get a string and returns the
 * compatible sorter
 */
public class SortStrategyFactory {

    public enum SortType {
        ABS{
            SortStrategy getSorterInstance() {
                return new Sorter(new AbsComparator());}},
        TYPE{
            SortStrategy getSorterInstance() {
                return new Sorter(new TypeComparator());}},
        SIZE{
            SortStrategy getSorterInstance() {
                return new Sorter(new SizeComparator());}};

        abstract SortStrategy getSorterInstance();
    }

    public static SortStrategy getSorter(String orderType,
                                         Integer orderLineNumber) throws BadOrderNameException {

        SortStrategy selectedSorter = null ;
        for (SortType sorterHolder : SortType.values()){
            if (sorterHolder.toString().toLowerCase().equals(orderType.toLowerCase())){
                selectedSorter = sorterHolder.getSorterInstance();
            }
        }
        if (selectedSorter == null) throw new BadOrderNameException(orderLineNumber);
        return selectedSorter;
    }
}
