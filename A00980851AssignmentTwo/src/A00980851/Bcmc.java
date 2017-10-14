package A00980851;

import A00980851.data.Customer;
import A00980851.data.Inventory;
import A00980851.data.Motorcycle;
import A00980851.db.controllers.CustomerController;
import A00980851.db.controllers.InventoryController;
import A00980851.db.controllers.MotrocycleController;
import A00980851.io.readers.CustomerReader;
import A00980851.io.readers.InventoryReader;
import A00980851.io.readers.MotorCycleReader;
import A00980851.ui.GuiController;
import A00980851.util.ApplicationException;
import A00980851.util.ShareableData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.util.LinkedList;

/**
 * Name Japneet Johal A00 980 851 Project Name AssignmentOne Class Name
 * A00980851.Bcmc Date 2017-05-28
 *
 * Assignment 1 - it is the driver of the program
 */
public class Bcmc {

	private static final String Log4J_CONFIG_FILE = "log4j2.xml";

	static {
		configureLogging();
	}

	private static final Logger LOG = LogManager.getLogger();

	LinkedList<Customer> customerLinkedList;
	LinkedList<Inventory> inventoryLinkedList;
	LinkedList<Motorcycle> motorCyclesArray;

	/**
	 * Main class of the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		LOG.info(""); // Blank Log.info is put here on purpose it makes it
						// easier to read the log.
		Instant startTime = Instant.now();

		LOG.info("Start Time " + startTime);

		new Bcmc().AssignmentOne(args);

	}

	/**
	 * This method takes the args and then gets linked lists and runs the
	 * application
	 * 
	 * @param args
	 */
	public void AssignmentOne(String[] args) {
		try {
			customerLinkedList = new CustomerReader().parse();
			inventoryLinkedList = new InventoryReader().parse();
			motorCyclesArray = new MotorCycleReader().parse();

			ShareableData.getInstance().setInventoryLinkedList(inventoryLinkedList);
			ShareableData.getInstance().setCustomerLinkedList(customerLinkedList);
			ShareableData.getInstance().setMotorcycleList(motorCyclesArray);

			InventoryController inventoryController = new InventoryController();
			inventoryController.load();

			CustomerController customerController = new CustomerController();
			customerController.load();

			MotrocycleController motrocycleController = new MotrocycleController();
			motrocycleController.load();

			GuiController guiController = new GuiController();
			guiController.launchGui();

		} catch (ApplicationException a) {
			LOG.error(a.getMessage());
		} catch (SQLException e) {
			LOG.error(e.getMessage());

		} catch (IOException e) {
			LOG.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			LOG.error(e.getMessage());
		}
	}

	/**
	 * this method configures the logging
	 */
	private static void configureLogging() {
		ConfigurationSource source;
		try {
			source = new ConfigurationSource(new FileInputStream(Log4J_CONFIG_FILE));
			Configurator.initialize(null, source);
		} catch (IOException e) {
			System.out.println("Cant find the log 4j File");
			LOG.error(e.getMessage());
		}
	}

}
