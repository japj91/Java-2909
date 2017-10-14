package A00980851;

import A00980851.jdbc.CreatingProperties;
import A00980851.jdbc.dao.DbTest;
import A00980851.data.Customer;
import A00980851.io.CustomerReader;
import A00980851.io.CustomerReport;
import A00980851.ui.Frame;
import A00980851.util.Compare;
import A00980851.util.Logging;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;


/**
 * Name            Japneet Johal A00 980 851
 * Project Name    A00980851Lab2
 * Class  Name     Lab9
 * Date            2017-05-03
 * <p>
 * This program the use of Exceptions, Dates
 */

public class Lab9 {
    public Lab9() {
    }

    /**
     * @param args - takes the Single long string
     */

    static Logging logging = new Logging();

    public static void main(String[] args) {

        LocalDateTime localDateTime = new Lab9().startTime();

        new Lab9().test("customers.txt",args);

        LocalDateTime localDateTime1 = new Lab9().finishTime();

        Duration duration = Duration.between(localDateTime1, localDateTime);

        logging.info(String.format("%s Milli Seconds\n", duration.abs().toMillis()));

    }

    /**
     * @param fileName - takes the file name of the input file
     */
    public void test(String fileName,String[] args) {

        CustomerReader customerReader = new CustomerReader();

        try {

            LinkedList<Customer> tempList = customerReader.returnList(fileName);


            DbTest dbDriver = new DbTest(tempList);

            CreatingProperties.createPropFile();

            CustomerReport customerReport = new CustomerReport();
            customerReport.printList(tempList);

            dbDriver.load(args);

            runGUI(dbDriver);
            logging.info("GUI stared");

        } catch (Exception ex) {
            logging.error(ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void runGUI(DbTest dbDriver) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Frame guiDevelopment = new Frame(dbDriver);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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
