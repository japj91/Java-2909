package A00980851.util;

import A00980851.data.Customer;
import A00980851.data.Inventory;
import A00980851.data.Motorcycle;

import java.util.LinkedList;

/**
 * Name Japneet Johal A00 980 851 Project Name A00980851AssignmentTwo Class Name
 * ShareableData Date 2017-06-24
 */
public class ShareableData {

	private final static ShareableData instance = new ShareableData();

	public static ShareableData getInstance() {
		return instance;
	}

	private LinkedList<Inventory> inventoryLinkedList = new LinkedList<>();
	private LinkedList<Customer> customerLinkedList = new LinkedList<>();
	private LinkedList<Motorcycle> motorcycleList = new LinkedList<>();

	public LinkedList<Inventory> getInventoryLinkedList() {
		return inventoryLinkedList;
	}

	public void setInventoryLinkedList(LinkedList<Inventory> inventoryLinkedList) {
		this.inventoryLinkedList = inventoryLinkedList;
	}

	public void setCustomerLinkedList(LinkedList<Customer> linkedList) {
		this.customerLinkedList = linkedList;
	}

	public LinkedList<Customer> getCustomersList() {
		return customerLinkedList;
	}

	public LinkedList<Motorcycle> getMotorcycleList() {
		return motorcycleList;
	}

	public void setMotorcycleList(LinkedList<Motorcycle> motorcycleList) {
		this.motorcycleList = motorcycleList;
	}
}
