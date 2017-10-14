package A00980851.util;

/**
 * Name            Japneet Johal A00 980 851
 * Project Name    AssignmentOne
 * Class  Name     StringPrinting
 * Date            2017-05-30
 * creates a string for the user. Used when errors appear. For example if customer needs 7 args but only has 6 will then show the 6 arguments in the log
 */
public abstract class StringPrinting {
    /**
     * generic method to create string when their is a error
     * @param customerInfo
     * @return
     */
    public static Object getString(String[] customerInfo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (String x: customerInfo){
            stringBuilder.append("|"+x+"|");
        }
        stringBuilder.append("}");
        return stringBuilder;
    }
}
