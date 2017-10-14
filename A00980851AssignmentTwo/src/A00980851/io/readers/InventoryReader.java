package A00980851.io.readers;

import A00980851.data.Inventory;
import A00980851.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import A00980851.util.ApplicationException;
import A00980851.util.StringPrinting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Name Japneet Johal A00 980 851 Project Name AssignmentOne Class Name
 * InventoryReader Date 2017-05-28
 */
public class InventoryReader extends StringPrinting {
	private final String FILENAME = "inventory.dat";
	private final String PARSETOKEN = "\\|";
	Validator validator;

	private static final Logger LOG = LogManager.getLogger();

	/**
	 * driver of the parse class
	 * 
	 * @return
	 * @throws ApplicationException
	 */
	public LinkedList<Inventory> parse() throws ApplicationException {
		LOG.info("Reading Inventory objects");

		validator = new Validator();
		Scanner scanner = openFile();
		LinkedList<String> customerStrings = readFile(scanner);
		LOG.info("Finished reading Inventory objects");
		return praseStrings(customerStrings);
	}

	/**
	 * opens the file
	 * 
	 * @return
	 * @throws ApplicationException
	 */
	private Scanner openFile() throws ApplicationException {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(FILENAME));
		} catch (FileNotFoundException ex) {
			throw new ApplicationException(String.format("File %s not found inventory Reader Class", FILENAME));
		}
		return scanner;
	}

	/**
	 * reads in the file
	 * 
	 * @param scanner
	 * @return
	 */
	private LinkedList<String> readFile(Scanner scanner) {

		scanner.nextLine();
		LinkedList<String> customers = new LinkedList<>();
		while (scanner.hasNextLine()) {
			String entry = scanner.nextLine();
			customers.add(entry);
		}
		LOG.debug(String.format("Added  %s customer objects to list from file ", customers.size()));
		return customers;
	}

	/**
	 * parses the strings
	 * 
	 * @param customerStrings
	 * @return
	 * @throws ApplicationException
	 */
	private LinkedList<Inventory> praseStrings(LinkedList<String> customerStrings) throws ApplicationException {

		LinkedList<Inventory> inventoryLinkedList = new LinkedList<>();

		for (int i = 0; i < customerStrings.size(); i++) {

			String[] inventoryInfo = customerStrings.get(i).split(PARSETOKEN);
			inventoryLinkedList.add(makeInventory(inventoryInfo));
		}
		return inventoryLinkedList;
	}

	/**
	 * checks arguemnt length and fills in objects
	 * 
	 * @param customerInfo
	 * @return
	 * @throws ApplicationException
	 */
	private Inventory makeInventory(String[] customerInfo) throws ApplicationException {

		if (customerInfo.length != 5) {
			throw new ApplicationException(String.format(
					"Not enough arguments for inventoryReadingClass %s System Exited", getString(customerInfo)));
		}

		String makeModel = (customerInfo[0]);

		String description = customerInfo[1];

		String partnumber = customerInfo[2];

		float temp = Integer.valueOf(customerInfo[3]);
		temp /= 100;
		String price = String.valueOf(temp);

		String quantity = customerInfo[4];

		Inventory inventory = new Inventory(makeModel, description, partnumber, price, quantity);

		return inventory;
	}
}
