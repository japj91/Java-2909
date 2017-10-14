package A00980851.io;

import A00980851.data.Customer;

import java.util.LinkedList;

/**
 * Name            Japneet Johal A00 980 851
 * Project Name    A00980851Lab2
 * Class  Name     CustomerReport
 * Date            2017-05-03
 * class to print the customer objctes
 */
public class CustomerReport {

    public CustomerReport() {
    }

    /**
     * @param customersList - Takes a list of Customer Objects
     */
    public void printList(LinkedList<Customer> customersList) {
        System.out.println("Identifier\tFirst Name\tLast Name\tStreet Name\t\t\t\t\tCity\t\t\tPostal\t\t\tPhone\t\t\t    Email\t\t\t\t\t   Join Date");
        for (Customer cust : customersList) {
            System.out.printf("%06d\t\t%-13s%-10s\t%-20s\t\t%-10s\t\t%-10s\t\t%s\t\t%-20s\t   %s-%s-%s\n",
                    cust.getIdentifier(), cust.getFirstName(), cust.getLastName(), cust.getStreetName(), cust.getPostalCode(), cust.getCity(), cust.getPhoneNumber(), cust.getEmailAddress(), cust.getDate().getMonth(), cust.getDate().getDayOfMonth(), cust.getDate().getYear());
        }


    }

}
