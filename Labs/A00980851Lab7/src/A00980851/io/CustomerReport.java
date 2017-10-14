package A00980851.io;

import A00980851.data.Customer;
import A00980851.util.Logging;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * Name            Japneet Johal A00 980 851
 * Project Name    A00980851Lab2
 * Class  Name     CustomerReport
 * Date            2017-05-03
 * class to print the customer objects
 */
public class CustomerReport {

    PrintWriter outputStream;
    Logging logging = new Logging();

    public CustomerReport() {
    }

    /**
     * @param customersList - Takes a list of Customer Objects and wrties them to the customer_report.txt file
     */
    public void printList(LinkedList<Customer> customersList) throws IOException {

        String fileName = "customers_report.txt";
        try {
            outputStream = new PrintWriter(new FileWriter(fileName));
            outputStream.println(("Identifier\tFirst Name\tLast Name\tStreet Name\t\t\t\t\tCity\t\t\tPostal\t\t\tPhone\t\t\t    Email\t\t\t\t\t   Join Date"));

            for (Customer cust : customersList) {
                outputStream.printf("%06d\t\t%-13s%-10s\t%-20s\t\t%-10s\t\t%-10s\t\t%s\t\t%-20s\t   %s-%s-%s\n",
                        cust.getIdentifier(), cust.getFirstName(), cust.getLastName(), cust.getStreetName(), cust.getPostalCode(),
                        cust.getCity(), cust.getPhoneNumber(), cust.getEmailAddress(), cust.getDate().getMonth(), cust.getDate().getDayOfMonth(),
                        cust.getDate().getYear());
            }
        }
        catch (FileNotFoundException ex){
            logging.error(ex.getMessage());
            ex.printStackTrace();
        }
        finally {
            outputStream.close();
        }

    }
    public void printToConsole(LinkedList<Customer> customerLinkedList){
        System.out.println(("Identifier\tFirst Name\tLast Name\tStreet Name\t\t\t\t\tCity\t\t\tPostal\t\t\tPhone\t\t\t    Email\t\t\t\t\t   Join Date"));
        for (Customer cust : customerLinkedList) {
            System.out.printf("%06d\t\t%-13s%-10s\t%-20s\t\t%-10s\t\t%-10s\t\t%s\t\t%-20s\t   %s-%s-%s\n",
                    cust.getIdentifier(), cust.getFirstName(), cust.getLastName(), cust.getStreetName(), cust.getPostalCode(),
                    cust.getCity(), cust.getPhoneNumber(), cust.getEmailAddress(), cust.getDate().getMonth(), cust.getDate().getDayOfMonth(),
                    cust.getDate().getYear());
        }
    }
}
