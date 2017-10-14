package A00980851.io.readers;

import A00980851.data.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import A00980851.util.ApplicationException;
import A00980851.util.StringPrinting;
import A00980851.util.Validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;



/**
 * Name            Japneet Johal A00 980 851
 * Project Name    AssignmentOne
 * Class  Name     CustomerReader
 * Date            2017-05-28
 * Class parses the customer.dat file
 */

public class CustomerReader extends StringPrinting {
    private final String FILENAME = "Customers.dat";
    private final String PARSETOKEN = "\\|";
    Validator validator;

    private static  final Logger LOG = LogManager.getLogger();
    /**
     *  Driver of this class
     * @return
     * @throws ApplicationException
     */
    public LinkedList<Customer> parse( ) throws ApplicationException {
        LOG.info("Reading customer objects");
        validator = new Validator();

        Scanner scanner = openFile();
        LinkedList<String> customerStrings = readFile(scanner);
        LOG.info("Finished reading customer objects ");

        return praseStrings(customerStrings);
    }

    /**
     * opening file
     * @return
     * @throws ApplicationException
     */
    private Scanner openFile()throws ApplicationException {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(FILENAME));
        }catch (FileNotFoundException ex){
            throw new ApplicationException(String.format("File %s not found CustomerReader Class",FILENAME));
        }
        return scanner;
    }

    /**
     * reading the file
     * @param scanner
     * @return
     */
    private LinkedList<String> readFile(Scanner scanner) {

        scanner.nextLine();
        LinkedList<String> customers = new LinkedList<>();
        while (scanner.hasNextLine()){
            String entry = scanner.nextLine();
            customers.add(entry);
        }
        LOG.debug(String.format("read in %s customer objects ",customers.size()));
        return customers;
    }

    /**
     *
     * @param customerStrings
     * @return
     * @throws ApplicationException
     */
    private LinkedList<Customer> praseStrings(LinkedList<String> customerStrings) throws ApplicationException {

        LinkedList<Customer> customerLinkedList = new LinkedList<>();
        for (int i =0; i<customerStrings.size();i++){
            String[] customerInfo = customerStrings.get(i).split(PARSETOKEN);
            customerLinkedList.add(makeCustomer(customerInfo));
        }
        return customerLinkedList;
    }

    /**
     * parsing each individual entry
     * @param customerInfo
     * @return
     * @throws ApplicationException
     */
    private Customer makeCustomer(String[] customerInfo) throws ApplicationException {

        if (customerInfo.length != 9){
            throw new ApplicationException(String.format("Not enough Arguments %s in the customer class",getString(customerInfo)));
        }

        String identifier = (customerInfo[0]);

        String firstName = customerInfo[1];

        String lastName = customerInfo[2];

        String streetName = customerInfo[3];

        String city = customerInfo[4];

        String postalCode = customerInfo[5];

        String phoneNumber = customerInfo[6];

        String emailAddress = validator.emailCheck(customerInfo[7]);

        LocalDate date = getDate(customerInfo[8]);

        Customer customer = new Customer(identifier,firstName,lastName,streetName,city,postalCode,
                phoneNumber,emailAddress,date);

        return customer;
    }

    /**
     * Creates a date object and tests the values of date
     * @param dateString
     * @return
     */
    private LocalDate getDate(String dateString) {
        int year = Integer.valueOf(dateString.substring(0,4));
        int month = Integer.valueOf( dateString.substring(4,6));
        int day = Integer.valueOf(dateString.substring(6,dateString.length()));
        if (month >12 || month==0){
            // add log info
            System.out.println("Add log info get date method in CustomerReader");
        }else if (day>31){
            System.out.println("Add log info get date method in CustomerReader");
        }

        LocalDate localDate = LocalDate.of(year,month,day);
        return localDate;
    }


}
