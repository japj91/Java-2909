/**
 * Project: A00123456Lab4
 * File: Lab4.java
 */

package examples.logging.a00123456;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import examples.logging.a00123456.data.customer.Customer;
import examples.logging.a00123456.data.customer.CustomerReader;
import examples.logging.a00123456.data.customer.CustomerReport;

/**
 * To demonstrate knowledge Generics and Collections and Logging
 * 
 * Create a commandline program which reads customer data, creates Customer objects, adds them to a collection, and prints a simple Customer report
 * sorted by birthdate.
 * Then add some logging
 * 
 * @author Sam Cirka, A00123456
 *
 */
public class LoggingTest {

	public static final String INPUT_STRING = "1|Fred|Fish|5707 Sidley St|Burnaby|V5J 5E6|(604) 433-5004|fredfish@imperial.net|20080322:2|Laurie|Nash|2816 Commercial Dr|Vancouver|V5N 4C6|(604) 876-0182|laurieeenash@modern.com|20140828:3|Conrad|Washington|13479 King George Blvd|Surrey|V3T 2T8|(604) 588-4988|cwash@daytona.net|20110712:4|Jeanette|Price|21000 Westminster Hwy|Richmond|V6V 2S9|(604) 276-2552|priceizrite@pacific.com|20151003:5|Mark|Chan|3766 E 1st Ave|Burnaby|V5C 3V9|(604) 293-1022|mchan@hcenter.com|20100401";
	public static final String INCORRECT_ARG_COUNT_INPUT_STRING = "Fish|5707 Sidley St|Burnaby|V5J 5E6|(604) 433-5004|fredfish@imperial.net|20080322:2|Laurie|Nash|2816 Commercial Dr|Vancouver|V5N 4C6|(604) 876-0182|laurieeenash@modern.com|20140828:3|Conrad|Washington|13479 King George Blvd|Surrey|V3T 2T8|(604) 588-4988|cwash@daytona.net|20110712:4|Jeanette|Price|21000 Westminster Hwy|Richmond|V6V 2S9|(604) 276-2552|priceizrite@pacific.com|20151003:5|Mark|Chan|3766 E 1st Ave|Burnaby|V5C 3V9|(604) 293-1022|mchan@hcenter.com|20100401";
	public static final String BAD_EMAIL_INPUT_STRING = "1|Fred|Fish|5707 Sidley St|Burnaby|V5J 5E6|(604) 433-5004|fredfishimperial.net|20080322:2|Laurie|Nash|2816 Commercial Dr|Vancouver|V5N 4C6|(604) 876-0182|laurieeenash@modern.com|20140828:3|Conrad|Washington|13479 King George Blvd|Surrey|V3T 2T8|(604) 588-4988|cwash@daytona.net|20110712:4|Jeanette|Price|21000 Westminster Hwy|Richmond|V6V 2S9|(604) 276-2552|priceizrite@pacific.com|20151003:5|Mark|Chan|3766 E 1st Ave|Burnaby|V5C 3V9|(604) 293-1022|mchan@hcenter.com|20100401";
	public static final String BAD_DATE_INPUT_STRING = "1|Fred|Fish|5707 Sidley St|Burnaby|V5J 5E6|(604) 433-5004|fredfish@imperial.net|20080322:2|Laurie|Nash|2816 Commercial Dr|Vancouver|V5N 4C6|(604) 876-0182|laurieeenash@modern.com|20140828:3|Conrad|Washington|13479 King George Blvd|Surrey|V3T 2T8|(604) 588-4988|cwash@daytona.net|20110712:4|Jeanette|Price|21000 Westminster Hwy|Richmond|V6V 2S9|(604) 276-2552|priceizrite@pacific.com|20151003:5|Mark|Chan|3766 E 1st Ave|Burnaby|V5C 3V9|(604) 293-1022|mchan@hcenter.com|20100499";

	public static final String LOG4J_CONFIG_FILENAME = "log4j2.xml";
	static {
		configureLogging();
	}
	private static final Logger LOG = LogManager.getLogger();

	private static String customerData;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Instant startTime = Instant.now();
		LOG.error(startTime);

		try {
			CliOptions.process(args);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}

		customerData = INPUT_STRING;

		if (CliOptions.isTestArgOptionSet()) {
			customerData = INCORRECT_ARG_COUNT_INPUT_STRING;
		} else if (CliOptions.isTestEmailOptionSet()) {
			customerData = BAD_EMAIL_INPUT_STRING;
		} else if (CliOptions.isTestDateOptionSet()) {
			customerData = BAD_DATE_INPUT_STRING;
		}

		new LoggingTest().run();
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
			System.err.println(String.format("Can't find the log4j logging configuration file %s.", LOG4J_CONFIG_FILENAME));
		}
	}

	/**
	 * Populate the customers and print them out.
	 */
	private void run() {
		try {
			Customer[] customers = CustomerReader.read(customerData);

			CustomerReport.write(customers);
		} catch (ApplicationException e) {
			System.out.println(e.getMessage());
		}
	}

}
