package A00980851.io;

import A00980851.data.Customer;
import A00980851.util.Logging;
import A00980851.util.Validadtor;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;


/**
 * Name            Japneet Johal A00 980 851
 * Project Name    A00980851Lab2
 * Class  Name     CustomerReader
 * Date            2017-05-03
 * class takes a String and formats it
 */
public class CustomerReader {

    Validadtor validator;
    Logging logging;
    public CustomerReader() {
        logging = new Logging();
    }

    /**
     * @param fileName - takes the input fileName as a string
     * @return a list of customers
     */

    public LinkedList<Customer> returnList(String fileName) throws ApplicationException {

        validator = new Validadtor();

        LinkedList<Customer> customerLinkedList = readFile(fileName);

        return customerLinkedList;
    }

    public LinkedList<Customer> readFile(String fileName) throws ApplicationException{
        Scanner scanner = null;
        String line;
        LinkedList<Customer> customerLinkedList = new LinkedList<>();
        try {
            scanner = new Scanner(new File(fileName));
            scanner.nextLine();
            while (scanner.hasNextLine()){
                line = scanner.nextLine();
                customerLinkedList.add(format(line));
            }
        }
        catch (FileNotFoundException ex){
            logging.error(ex.getMessage());
            System.exit(0);
        }
        scanner.close();


        return customerLinkedList;

    }

    @SuppressWarnings("No longer used after lab 4")
    /**
     * @param list - splits the string on the :
     * @return a list of each entry
     */
    public LinkedList<String> split(String list) {
        String[] splitList = list.split(":");

        LinkedList<String> temmpp = new LinkedList<String>(Arrays.asList(splitList));
        return temmpp;
    }

    /**
     * @param individualEntry - further splits the list on the |
     * @return a customer object
     */

    public Customer format(String individualEntry) throws ApplicationException {

        String[] tempList = individualEntry.split("\\|");

        if (tempList.length != 9) {
            logging.error(String.format("Expected 9 arguments got %s the bad data was\n%s", tempList.length, individualEntry));
            throw new ApplicationException();
        }

        int identifier = Integer.valueOf(tempList[0]);

        String firstName = tempList[1];

        String lastName = tempList[2];

        String streetName = tempList[3];

        String city = tempList[4];

        String postalCode = tempList[5];

        String phoneNumber = tempList[6];

        String emailAddress = validator.emailCheck(tempList[7]);

        LocalDate date = getCalendar(tempList[8]);

        Customer.Builder builder = new Customer.Builder(identifier, phoneNumber);

        building(builder, firstName, lastName, streetName, city, postalCode, emailAddress, date);

        Customer customer = builder.build();

        return customer;

    }

    /**
     * @param entry - the calendar entry
     * @return - A string of a Calendar
     * @throws ApplicationException
     */
    private LocalDate getCalendar(String entry) throws ApplicationException {
        int year = Integer.valueOf(entry.substring(0, 4));
        int month = Integer.valueOf(entry.substring(4, 6));
        int day = Integer.valueOf(entry.substring(6, 8));

        if (month > 12) {

            logging.error("Months are greater then 12");

            throw new ApplicationException("The date " + month + " for months is invalid");
        }
        else if (day > 31) {

            logging.error("More then 31 days entered for the month");
            throw new ApplicationException("The date " + day + " for data is invalid");
        }

        LocalDate fromIsoDate = LocalDate.of(year, month, day);

        return fromIsoDate;
    }


    /**
     * private helper class
     *
     * @param builder
     * @param firstName
     * @param lastName
     * @param streetName
     * @param city
     * @param postalCode
     * @param emailAddress takes above arguments and prints them
     */
    private void building(Customer.Builder builder, String firstName, String lastName, String streetName, String city, String postalCode, String emailAddress, LocalDate date) {

        // you have builder object to set each of these params you need to go builder.{something} to do it

        builder.firstName(firstName);

        builder.lastName(lastName);

        builder.streetName(streetName);

        builder.city(city);

        builder.postalCode(postalCode);

        builder.emailAddress(emailAddress);

        builder.date(date);
    }
}
