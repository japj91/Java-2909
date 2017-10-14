package A00980851;

import A00980851.data.Customer;
import A00980851.io.ApplicationException;
import A00980851.io.CustomerReader;
import A00980851.io.CustomerReport;
import A00980851.util.Compare;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;


/**
 * Name            Japneet Johal A00 980 851
 * Project Name    A00980851Lab2
 * Class  Name     Lab4
 * Date            2017-05-03
 * <p>
 * This program the use of Exceptions, Dates
 */

public class Lab4 {
    public Lab4() {
    }

    /**
     * @param args - takes the Single long string
     */

    public static void main(String[] args) {

        LocalDateTime localDateTime = new Lab4().startTime();

        if (args.length != 1) {
            System.out.println("Missing argument- System Exited");
            System.exit(-1);
        }
        new Lab4().test(args[0]);

        LocalDateTime localDateTime1 = new Lab4().finishTime();

        Duration duration = Duration.between(localDateTime1, localDateTime);
        System.out.printf("%s Milli Seconds\n", duration.abs().toMillis());
    }

    /**
     * @param list - takes the Single long string that is entered in as program arguments
     */
    public void test(String list) {

        CustomerReader customerReader = new CustomerReader();

        try {
            LinkedList<Customer> tempList = customerReader.returnList(list);
            Sorting(tempList); // sort the list
            CustomerReport customerReport = new CustomerReport();

            customerReport.printList(tempList);
        } catch (ApplicationException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Sorting the list based on the date they joined
     *
     * @param tempList
     */
    private void Sorting(LinkedList<Customer> tempList) {
        tempList.sort(new Compare.compareByJoinDate());
    }



    /**
     * @return - return the LocalDateTime when the mehtod was called
     */
    public LocalDateTime startTime() {
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDateTime);

        System.out.println();

        return localDateTime;
    }

    /**
     * @return the LocalDateTime when the mehtod was called
     */
    public LocalDateTime finishTime() {
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println();

        System.out.println(localDateTime);

        return localDateTime;
    }
}
