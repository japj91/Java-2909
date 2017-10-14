package A00980851.ui;

import A00980851.data.Inventory;
import A00980851.db.controllers.InventoryController;
import A00980851.util.ShareableData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Name Japneet Johal A00 980 851 Project Name A00980851AssignmentTwo Class Name
 * GuiController Date 2017-06-24
 */
public class GuiController {

	LinkedList<Inventory> inventoryLinkedList = ShareableData.getInstance().getInventoryLinkedList();
	InventoryTable inventoryDialog;
	final String EMPTY = "";

	private static final Logger LOG = LogManager.getLogger();

	/**
	 * launches the GUI
	 */
	public void launchGui() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					LOG.error(e.getMessage());

				}
			}
		});
	}

	/**
	 * gets the total of all inventory and displays it
	 * @return
	 */
	public double getTotal() {
		LOG.info("User selected get total");
		double total = 0;
		for (Inventory inventory : inventoryLinkedList) {
			total += Double.valueOf(inventory.getPrice()) * Double.valueOf(inventory.getQuantity());
		}
		return total;
	}

	/**
	 * sorts based on description if descending is true
	 * @param descendingStatus
	 */
	public void getDesc(boolean descendingStatus) {

		if (descendingStatus) {
			inventoryLinkedList.sort((s1, s2) -> s1.getDescription().compareTo(s2.getDescription()) * -1);
			LOG.info("User selected organize by description in ascending order");
		} else {
			inventoryLinkedList.sort((s1, s2) -> s1.getDescription().compareTo(s2.getDescription()));
			LOG.info("User selected organize by description");
		}
		inventoryDialog = new InventoryTable();
		inventoryDialog.run(getTableModel(inventoryLinkedList));

	}

	/**
	 * sorts based on if count if status is true true
	 * @param status
	 */
	public void getCount(boolean status) {

		if (status) {
			inventoryLinkedList.sort(
					(s1, s2) -> Double.valueOf(s1.getQuantity()).compareTo(Double.valueOf(s2.getQuantity())) * -1);
			LOG.info("User selected organize by count in ascending order");

		} else {
			inventoryLinkedList
					.sort((s1, s2) -> Double.valueOf(s1.getQuantity()).compareTo(Double.valueOf(s2.getQuantity())));
			LOG.info("User selected organize by count");
		}
		inventoryDialog = new InventoryTable();
		inventoryDialog.run(getTableModel(inventoryLinkedList));

	}
	/**
	 * sorts based on make if desncingStatus true
	 * @param descendingStatus
	 */
	public void getMake(boolean descendingStatus) {
		LOG.info("User asked to find a specific make");
		String userResponse = JOptionPane.showInputDialog(null, "Tell the make you like?", "maker",
				JOptionPane.INFORMATION_MESSAGE);
		LinkedList<Inventory> linkedList = new LinkedList<>();

		if (userResponse.equals(EMPTY)) {
			LOG.info("User response is equal to null");
			InventoryTable inventoryDialog = new InventoryTable();
			inventoryDialog.run(getTableModel(inventoryLinkedList));
		} else {
			for (Inventory inventory : inventoryLinkedList) {

				int breakPoint = inventory.getMotorCyleID().indexOf("+");
				String model = inventory.getMotorCyleID().substring(0, breakPoint); // getting the model is the chars before the + sign
				addMatchingToList(model, userResponse, linkedList, inventory);
			}
			isListEmpty(userResponse, linkedList);
		}
	}

	/**
	 * checks if list is empty if it is gives user error message
	 * @param userResponse
	 * @param linkedList
	 */
	private void isListEmpty(String userResponse, LinkedList<Inventory> linkedList) {
		if (linkedList.size() == 0) {
			JOptionPane.showMessageDialog(null, "No makes in inventory found with name " + userResponse,
					"Model Search ", JOptionPane.ERROR_MESSAGE);
		} else {
			LOG.info("Matches were found for users query of " + userResponse);
			InventoryTable inventoryTable = new InventoryTable();
			inventoryTable.run(getTableModel(linkedList));
		}
	}

	/**
	 * this mehtod is called form is list empty and adds matching models to a list
	 * @param model
	 * @param response
	 * @param linkedList
	 * @param inventory
	 */
	private void addMatchingToList(String model, String response, LinkedList<Inventory> linkedList, Inventory inventory) {
		if (model.toLowerCase().equals(response.toLowerCase())) {
			linkedList.add(inventory);
		}
	}

	/**
	 *  getting table model and adding it to a table and sending it to inventory
	 *  table to launch GUI
	 * @param list
	 * @return
	 */
	private JTable getTableModel(LinkedList<Inventory> list) {
		// makes the cells not editable in the table
		DefaultTableModel defaultTableModel = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		defaultTableModel.addColumn("ID");
		defaultTableModel.addColumn("Description");
		defaultTableModel.addColumn("Part_Number");
		defaultTableModel.addColumn("Price");
		defaultTableModel.addColumn("Quantity");
		// adding new objctes to the defaultTableModel
		for (int i = 0; i < list.size(); i++) {
			Inventory inventoryItem = list.get(i);
			defaultTableModel.addRow(new Object[] { inventoryItem.getMotorCyleID(), inventoryItem.getDescription(),
					inventoryItem.getPartNumber(), inventoryItem.getPrice(), inventoryItem.getQuantity() });
		}

		JTable table = new JTable(defaultTableModel);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					String name = (String) table.getValueAt(table.getSelectedRow(), 0);
					String partNumber = (String) table.getValueAt(table.getSelectedRow(), 2);

					showDialog(name, partNumber);
				}
			}
		});
		return table;
	}

	/**
	 * shows the dialog of pop up window
	 * @param name
	 * @param partNumber
	 */
	private void showDialog(String name, String partNumber) {
		InventoryController dbInterface = new InventoryController();
		try {
			Inventory inventory = dbInterface.search(name, partNumber);
			LOG.info("User selected the entry " + inventory.toString());
			new InventoryDialog(inventory, dbInterface);
		} catch (SQLException e) {
			LOG.error(e.getMessage());

		}
	}

	public void refreshData() {

	}

}
