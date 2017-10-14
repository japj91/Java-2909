package A00980851;

import A00980851.data.Customer;
import A00980851.io.CustomerReader;
import A00980851.io.CustomerReport;
import A00980851.util.Compare;
import A00980851.util.Logging;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;


/**
 * Name            Japneet Johal A00 980 851
 * Project Name    A00980851Lab2
 * Class  Name     Lab5
 * Date            2017-05-03
 * <p>
 * This program the use of Exceptions, Dates
 */

public class Lab5 {
    public Lab5() {
    }

    /**
     * @param args - takes the Single long string
     */

    static Logging logging = new Logging();

    public static void main(String[] args) {

        LocalDateTime localDateTime = new Lab5().startTime();

        new Lab5().test("customers.txt");

        LocalDateTime localDateTime1 = new Lab5().finishTime();

        Duration duration = Duration.between(localDateTime1, localDateTime);

        logging.info(String.format("%s Milli Seconds\n", duration.abs().toMillis()));

    }

    /**
     * @param fileName - takes the file name of the input file
     */
    public void test(String fileName) {

        CustomerReader customerReader = new CustomerReader();

        try {
            LinkedList<Customer> tempList = customerReader.returnList(fileName);
            Sorting(tempList); // sort the list
            CustomerReport customerReport = new CustomerReport();
            customerReport.printList(tempList);
        } catch (Exception ex) {
            logging.error(ex.getMessage());
        }
    }

    /**
     * Sorting the list based on the date they joined
     * You give sort the parameters you want it to compare by
     * @param tempList
     */
    private void Sorting(LinkedList<Customer> tempList) {
        tempList.sort(new Compare.compareByJoinDate());
    }


    /**
     * @return - logs the LocalDateTime when the mehtod was called
     */
    public LocalDateTime startTime() {
        LocalDateTime localDateTime = LocalDateTime.now();

        logging.info(String.valueOf(localDateTime));

        System.out.println();

        return localDateTime;
    }

    /**
     * @return logs LocalDateTime when the mehtod was called
     */
    public LocalDateTime finishTime() {
        LocalDateTime localDateTime = LocalDateTime.now();

        logging.info(String.valueOf(localDateTime));

        return localDateTime;
    }
}
