package filesprocessing;

/**
 * Represents a section in an order commands file.
 * Hold the data about the (checked and valid) requested for a filtering & sorting order.
 */
class Section {
    private String filterName;
    private String orderName;
    private String[] filterArgs;
    private String[] orderArgs;
    private Integer filterCommandLineNumber;
    private Integer orderCommandLineNumber;


    Section(String filterNameInput, String orderNameInput, String[] filterArgsInput,
            String[] orderArgsInput, Integer filterCommandLineNumberInput,
            Integer orderCommandLineNumberInput) {
        filterName = filterNameInput;
        orderName = orderNameInput;
        filterArgs = filterArgsInput;
        orderArgs = orderArgsInput;
        filterCommandLineNumber = filterCommandLineNumberInput;
        orderCommandLineNumber = orderCommandLineNumberInput;
    }

    String getFilterName() {return filterName;}
    String getOrderName() {return orderName;}
    String[] getFilterArgs() {return filterArgs;}
    String[] getOrderArgs() {return orderArgs;}
    Integer getFilerCommandLineNumber() {return filterCommandLineNumber;}
    Integer getOrderCommandLineNumber() {return orderCommandLineNumber;}
}


