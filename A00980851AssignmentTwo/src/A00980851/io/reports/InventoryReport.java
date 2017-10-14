package A00980851.io.reports;

import A00980851.data.Inventory;

import java.util.LinkedList;

/**
 * Name Japneet Johal A00 980 851 Project Name AssignmentOne Class Name
 * InventoryReport Date 2017-05-31
 */
public class InventoryReport {
	public void printCustomer(LinkedList<Inventory> inventoryLinkedList) {

		if (inventoryLinkedList.get(0).getTotal() == null) {
			System.out.println("Make+Model\t\t\t\t\t  Description\t\t\t\t\t  Part#\t\t\t\t\t\t  Price\t    Quantity");
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------");

			for (Inventory inventory : inventoryLinkedList)
				System.out.printf("%-30s %-30s %-28s%-10s %-6s\n", inventory.getMotorCyleID(),
						inventory.getDescription(), inventory.getPartNumber(), inventory.getPrice(),
						inventory.getQuantity());

		} else {
			float total = 0;
			System.out.println(
					"Make+Model\t\t\t\t\t  Description\t\t\t\t\t  Part#\t\t\t\t\t\t  Price\t    Quantity   Total");
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------------");

			for (Inventory inventory : inventoryLinkedList) {
				System.out.printf("%-30s %-30s %-28s%-10s %-6s   %.02f\n", inventory.getMotorCyleID(),
						inventory.getDescription(), inventory.getPartNumber(), inventory.getPrice(),
						inventory.getQuantity(), Float.valueOf(inventory.getTotal()));
				total += Float.valueOf(inventory.getTotal());
			}

			System.out.println(
					"----------------------------------------------------------------------------------------------------------------------");
			System.out.println("Total value of inventory is $" + total);

		}

	}
}
