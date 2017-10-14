/**
 * Project: A00123456Lab3
 * File: CustomerReader.java
 */

package examples.logging.a00123456.data.customer;

import java.time.DateTimeException;
import java.util.Arrays;

import examples.logging.a00123456.ApplicationException;
import examples.logging.a00123456.data.util.Validator;

/**
 * Read the customer input.
 * 
 * @author Sam Cirka, A00123456
 *
 */
public class CustomerReader {

	public static final String RECORD_DELIMITER = ":";
	public static final String FIELD_DELIMITER = "\\|";

	/**
	 * private constructor to prevent instantiation
	 */
	private CustomerReader() {
	}

	/**
	 * Read the customer input data.
	 * 
	 * @param data
	 *            The input data.
	 * @return An array of customers.
	 * @throws ApplicationException
	 */
	public static Customer[] read(String data) throws ApplicationException {
		String[] rows = data.split(RECORD_DELIMITER);
		Customer[] customers = new Customer[rows.length];
		int i = 0;

		for (String row : rows) {
			Customer customer = readCustomerString(row);
			customers[i++] = customer;
		}

		return customers;
	}

	/**
	 * Parse a Customer data string into a CUstomer object;
	 * 
	 * @param row
	 * @throws ApplicationException
	 */
	private static Customer readCustomerString(String data) throws ApplicationException {
		String[] elements = data.split(FIELD_DELIMITER);
		if (elements.length != Customer.ATTRIBUTE_COUNT) {
			throw new ApplicationException(
					String.format("Expected %d but got %d: %s", Customer.ATTRIBUTE_COUNT, elements.length, Arrays.toString(elements)));
		}

		int index = 0;
		long id = Integer.parseInt(elements[index++]);
		String firstName = elements[index++];
		String lastName = elements[index++];
		String street = elements[index++];
		String city = elements[index++];
		String postalCode = elements[index++];
		String phone = elements[index++];
		// should the email validation be performed here or in the customer class? I'm leaning towards putting it here.
		String emailAddress = elements[index++];
		if (!Validator.validateEmail(emailAddress)) {
			throw new ApplicationException(String.format("Invalid email: %s", emailAddress));
		}
		String yyyymmdd = elements[index];
		if (!Validator.validateJoinedDate(yyyymmdd)) {
			throw new ApplicationException(String.format("Invalid joined date: %s for customer %d", yyyymmdd, id));
		}
		int year = Integer.parseInt(yyyymmdd.substring(0, 4));
		int month = Integer.parseInt(yyyymmdd.substring(4, 6)) - 1;
		int day = Integer.parseInt(yyyymmdd.substring(6, 8));

		Customer customer = null;

		try {
			customer = new Customer.Builder(id, phone).setFirstName(firstName).setLastName(lastName).setStreet(street).setCity(city)
					.setPostalCode(postalCode).setEmailAddress(emailAddress).setJoinedDate(year, month, day).build();
		} catch (DateTimeException e) {
			throw new ApplicationException(e.getMessage());
		}

		return customer;
	}

}
