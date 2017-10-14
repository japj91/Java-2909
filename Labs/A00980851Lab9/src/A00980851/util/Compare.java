package A00980851.util;

import A00980851.data.Customer;

import java.util.Comparator;

/**
 * Name            Japneet Johal A00 980 851
 * Project Name    A00980851Lab4
 * Class  Name     Compare
 * Date            2017-05-14
 *
 *  Class created for comprasiion
 */
public class Compare {


    public static class compareByJoinDate implements Comparator<Customer> {
        /**
         * inner class here is to override the compare function basically defening own way to compare. Check example 4 for compareTo examples
         * compares two dates. Localdate.compareto returns a int then its knows which is first
         * @param o1 - the first customer
         * @param o2 - the second customer
         * @return a number based on their comprassion
         */
        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.getFirstName().compareTo(o2.getFirstName());
        }
    }
}
