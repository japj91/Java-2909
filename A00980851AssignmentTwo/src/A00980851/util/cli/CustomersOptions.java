package A00980851.util.cli;

import A00980851.data.Customer;
import A00980851.io.reports.CustomerReport;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

/**
 * Name Japneet Johal A00 980 851 Project Name AssignmentOne Class Name
 * CustomersOptions Date 2017-05-31 reads the customer arguments if present and
 * acts on them
 */
public class CustomersOptions {
	/**
	 * maind driver of this class checks arguemtns and executes options
	 * 
	 * @param args
	 * @param customerLinkedList
	 * @throws ParseException
	 */
	private static final Logger LOG = LogManager.getLogger();

	public void customersCliCheck(String[] args, LinkedList<Customer> customerLinkedList) throws ParseException {
		SetupCLI.process(args);

		if (SetupCLI.hasCustomers()) {
			LOG.info("The customers option has been chosen as a argument");
			CustomerReport customerReport = new CustomerReport();

			if (SetupCLI.hasJoinDate()) {
				customerLinkedList.sort((s1, s2) -> s1.getJoinDate().compareTo(s2.getJoinDate()));
			}

			if (SetupCLI.isDescenindingAny()) {
				customerLinkedList.sort((s1, s2) -> s1.getJoinDate().compareTo(s2.getJoinDate()) * -1); // comapres
																										// by
																										// first
																										// name
																										// in
																										// descending
																										// order
			}
			customerReport.printCustomer(customerLinkedList);
		}
	}
}
