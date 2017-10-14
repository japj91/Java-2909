/**
 * Project: A00123456Lab5
 * File: Lab5.java
 * Copyright 2017 Sam Cirka. All rights reserved.
 */

package a00123456;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import a00123456.data.Customer;
import a00123456.data.CustomerSorters;
import a00123456.io.CustomerReader;
import a00123456.io.CustomerReport;

/**
 * To demonstrate knowledge of File IO and (log4j) Logging
 * 
 * Create a commandline program which reads customer data, creates Customer objects, adds them to a collection, and prints
 * a simple Customer report sorted by joined date
 * 
 * @author Sam Cirka, A00123456
 *
 */
public class Lab5 {

	private static final String PERSONAS_DATA_FILENAME = "customers.txt";
	private static final String LOG4J_CONFIG_FILENAME = "log4j2.xml";

	static {
		configureLogging();
	}

	private static final Logger LOG = LogManager.getLogger();

	private List<Customer> customers;
	private File customerDataFile;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Instant startTime = Instant.now();

		LOG.info(startTime);

		File file = new File(PERSONAS_DATA_FILENAME);
		if (!file.exists()) {
			System.out.format("Required '%s' is missing.", PERSONAS_DATA_FILENAME);
			System.exit(-1);
		}

		new Lab5(file).run();
		Instant endTime = Instant.now();
		LOG.info(endTime);
		LOG.info(String.format("Duration: %d ms", Duration.between(startTime, endTime).toMillis()));
	}

	private static void configureLogging() {
		ConfigurationSource source;
		try {
			source = new ConfigurationSource(new FileInputStream(LOG4J_CONFIG_FILENAME));
			Configurator.initialize(null, source);

		} catch (IOException e) {
			System.out.println(String.format("Can't find the log4j logging configuration file %s.", LOG4J_CONFIG_FILENAME));
		}
	}

	/**
	 * Lab5 constructor.
	 */
	public Lab5(File customerDataFile) {
		LOG.debug("Lab5()");
		this.customerDataFile = customerDataFile;
	}

	/**
	 * Populate the customers and print them out.
	 */
	private void run() {
		LOG.debug("run()");
		try {
			loadCustomers();

			// sort the customers by joined date
			Collections.sort(customers, new CustomerSorters.CompareByJoinedDate());
			// just for fun. here's what sorting would look like if you used lambda expressions
			// customers.sort((e1, e2) -> e1.getJoinedDate().compareTo(e2.getJoinedDate()));

			printCustomers();
		} catch (ApplicationException e) {
			LOG.error(e.getMessage());
		}
	}

	private void loadCustomers() throws ApplicationException {
		LOG.debug(String.format("Reading the Customers Report from '%s'", customerDataFile.getAbsolutePath()));
		customers = CustomerReader.read(customerDataFile);
	}

	private void printCustomers() {
		File customersFile = new File("customers_report.txt");
		LOG.debug(String.format("Writing the Customers Report to '%s'", customersFile.getAbsolutePath()));
		PrintStream out = null;
		try {
			out = new PrintStream(new FileOutputStream(customersFile));
			CustomerReport.write(customers, out);
		} catch (FileNotFoundException e) {
			LOG.error(e.getMessage());
		}
	}

}
