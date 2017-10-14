package A00980851.io.reports;

import A00980851.data.Motorcycle;

import java.util.List;

/**
 * Name Japneet Johal A00 980 851 Project Name AssignmentOne Class Name
 * MotorCycleReport Date 2017-05-31
 */
public class MotorCycleReport {

	public void print(List<Motorcycle> customerLinkedList) {
		System.out.println("ID\t\t   Make\t\t\t\t\tModel\t\t      Year\t\t\t Serial_Number\t\t   Mileage");

		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------");
		for (Motorcycle motorcycle : customerLinkedList) {
			System.out.printf("%-10s %-20s %-18s%-15s %-20s %-13s\n", motorcycle.getMotorcycle_ID(),
					motorcycle.getMake(), motorcycle.getModel(), motorcycle.getYear(), motorcycle.getSerialNumber(),
					motorcycle.getMileage());
		}
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------");
	}
}
