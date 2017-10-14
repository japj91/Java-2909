package A00980851.io.reports;

import A00980851.data.Customer;

import java.util.LinkedList;

/**
 * Name            Japneet Johal A00 980 851
 * Project Name    AssignmentOne
 * Class  Name     CustomerReport
 * Date            2017-05-31
 */
public class CustomerReport {

    public void printCustomer(LinkedList<Customer> customerLinkedList){
        System.out.println("ID\t\t   FIRST_NAME\tLAST_NAME\t    STREET\t\t\t\t\t  CITY\t\t\t\t  POSTAL_CODE\tPHONE\t\t\t\t EMAIL\t\t\t\t\t        JOIN_DATE");

        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Customer customer:customerLinkedList){
            System.out.printf("%-10s %-10s %-18s%-25s %-20s %-13s %-20s%-30s %s\n",customer.getId(),customer.getFirstName(),customer.getLastName(),
                    customer.getStreet(),customer.getCity(),customer.getPostalCode(),customer.getPhone(),customer.getEmail(),customer.getJoinDate());
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
}
