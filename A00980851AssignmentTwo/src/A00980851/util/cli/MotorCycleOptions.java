package A00980851.util.cli;

import A00980851.data.Motorcycle;
import A00980851.io.reports.MotorCycleReport;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Name Japneet Johal A00 980 851 Project Name AssignmentOne Class Name
 * MotorCycleOptions Date 2017-06-01 This class takes motorcycle command
 * arguments and parses them
 */
public class MotorCycleOptions {
	/**
	 * checks individual arguments and acts on them
	 * 
	 * @param args
	 * @param motorcycleLinkedList
	 * @throws ParseException
	 */
	private static final Logger LOG = LogManager.getLogger();

	public void checkOptions(String[] args, Motorcycle[] motorcycles) throws ParseException {
		SetupCLI.process(args);
		LinkedList<Motorcycle> motorcycleLinkedList = new LinkedList<>(Arrays.asList(motorcycles));

		if (SetupCLI.hasService()) {
			LOG.info("The motorcycle option from CLI has been choosen");
			MotorCycleReport motorCycleReport = new MotorCycleReport();

			if (SetupCLI.hasmake() != null) {
				String type = SetupCLI.hasmake();
				filter(motorcycleLinkedList, type);
			}

			motorCycleReport.print(motorcycleLinkedList);
		}
	}

	/**
	 * used when the person adds the make method
	 * 
	 * @param motorcycleLinkedList
	 * @param type
	 */
	private void filter(LinkedList<Motorcycle> motorcycleLinkedList, String type) {
		Iterator<Motorcycle> itr = motorcycleLinkedList.iterator();

		while (itr.hasNext()) {
			String entry = itr.next().getMake();
			if (!entry.equals(type)) {
				itr.remove();
			}
		}
	}

}
