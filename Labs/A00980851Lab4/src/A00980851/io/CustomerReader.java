package A00980851.io;

import A00980851.data.Customer;
import A00980851.util.Validadtor;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;


/**
 * Name            Japneet Johal A00 980 851
 * Project Name    A00980851Lab2
 * Class  Name     CustomerReader
 * Date            2017-05-03
 * class takes a String and formats it
 */
public class CustomerReader {

    Validadtor validator;

    public CustomerReader() {
    }

    /**
     * @param unformattedData - takes the single long String
     * @return a list of customers
     */
    public LinkedList<Customer> returnList(String unformattedData) throws ApplicationException {

        validator = new Validadtor();

        LinkedList<String> temp = split(unformattedData);

        LinkedList<Customer> customerArrayList = new LinkedList<>();


        for (int i = 0; i < temp.size(); i++) {

            String individualEntry = temp.get(i);

            Customer tempCustomer = format(individualEntry);

            customerArrayList.add(tempCustomer);

        }
        return customerArrayList;
    }

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
            throw new ApplicationException(String.format("Expected 9 arguments got %s the bad data was\n%s", tempList.length, individualEntry));
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
            throw new ApplicationException("The date " + month + " for months is invalid");
        } else if (day > 31) {
            throw new ApplicationException("The date " + day + " for data is invalid");
        }

        LocalDate fromIsoDate = LocalDate.of(year, month, day);
        //String date = String.format("%s %s %s", fromIsoDate.getMonth(), fromIsoDate.getDayOfMonth(), fromIsoDate.getYear());

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
