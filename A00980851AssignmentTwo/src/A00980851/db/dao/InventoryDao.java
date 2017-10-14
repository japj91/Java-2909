package A00980851.db.dao;

import A00980851.data.Inventory;
import A00980851.db.setup.DatabaseSetup;
import A00980851.db.setup.DbConstants;
import A00980851.util.ShareableData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;

/**
 * Name Japneet Johal A00 980 851 Project Name A00980851AssignmentTwo Class Name
 * InventoryDao Date 2017-06-21
 */
public class InventoryDao extends Dao {

	static final String TABLE_NAME = DbConstants.DB_TABLE_NAME;
	static Connection connection;
	private static final Logger log = LogManager.getLogger();

	/**
	 * default constructor
	 * 
	 * @param database
	 */
	public InventoryDao(DatabaseSetup database) {
		super(database, TABLE_NAME);
	}

	/**
	 * Takes a string and drops a entry from object
	 * 
	 * @param sql
	 * @throws SQLException
	 */

	public void drop(String sql) throws SQLException {

		String tableName = String.format("Drop table %s ", sql);
		connection = database.getConnection();
		Statement statement = connection.createStatement();

		if (DatabaseSetup.tableExists(sql)) {
			statement.executeUpdate(tableName);
		}
	}

	/**
	 * takes a list and each individual object is added to database
	 * 
	 * @param inventoryLinkedList
	 * @throws SQLException
	 */
	public void add(LinkedList<Inventory> inventoryLinkedList) throws SQLException {
		Statement statement = null;

		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			int count = 0;
			for (Inventory inventory : inventoryLinkedList) {
				String sql = String.format(" insert into %s values ('%s', '%s', '%s', '%s', '%s')",
						DbConstants.DB_TABLE_NAME, inventory.getMotorCyleID(), inventory.getDescription(),
						inventory.getPartNumber(), inventory.getPrice(), inventory.getQuantity());
				statement.executeUpdate(sql);
				count++;

			}
			log.info(String.format("DB ADDED %s INVENTORY ENTRIES", count));
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			statement.close();
		}
		log.info("Inventory data inserted sucessfully");
	}

	/**
	 * takes a object and uses its properties to delete an entry from the
	 * database
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void delete(String id) throws SQLException {
		connection = database.getConnection();
		Statement statement = connection.createStatement();

		String sql = String.format("delete from %s where identifier = '%s' ", DbConstants.DB_TABLE_NAME, id);
		statement.executeUpdate(sql);
		log.info("Entry with %s id in %s deleted from database", id, DbConstants.DB_TABLE_NAME);
	}

	/**
	 * takes a object and uses its properties to update the database
	 * 
	 * @param inventory
	 * @throws SQLException
	 */
	public void update(Inventory inventory) throws SQLException {
		Connection connection = database.getConnection();
		Statement statement = connection.createStatement();

		String sql = String.format(
				"update %s set %s = '%s', %s = '%s', %s = '%s', %s = '%s', %s = '%s' WHERE %s = '%s' and %s = '%s'",
				DbConstants.DB_TABLE_NAME, DbConstants.IDENTIFIER, inventory.getMotorCyleID(), DbConstants.DECRIPTION,
				inventory.getDescription(), DbConstants.PARTNUMBER, inventory.getPartNumber(), DbConstants.PRICE,
				inventory.getPrice(), DbConstants.QUANTITY, inventory.getQuantity(), DbConstants.IDENTIFIER,
				inventory.getMotorCyleID(), DbConstants.PARTNUMBER, inventory.getPartNumber());

		statement.executeUpdate(sql);
		log.debug("Inventory Updated");
	}

	/**
	 * takes a name and partNumber to find a inventory
	 * 
	 * @param name
	 * @param partNumber
	 * @return
	 * @throws SQLException
	 */
	public Inventory select(String name, String partNumber) throws SQLException {
		Connection connection = database.getConnection();
		Statement statement = connection.createStatement();
		String sql = String.format("Select * from %s where identifier = '%s' and partNumber = '%s' ",
				DbConstants.DB_TABLE_NAME, name, partNumber);

		ResultSet resultSet = statement.executeQuery(sql);
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

		int rowCount = resultSetMetaData.getColumnCount();

		String[] inventory = new String[6];
		while (resultSet.next()) {

			for (int i = 1; i <= rowCount; i++) {

				String data = resultSet.getString(i);
				inventory[i] = data;
			}
		}
		return new Inventory(inventory[1], inventory[2], inventory[3], inventory[4], inventory[5]);
	}

	@Override
	public void create() {
	}

	/**
	 * it gets a updated list from DB if the DB already exists
	 * 
	 * @throws SQLException
	 */
	public void getList() throws SQLException {
		LinkedList<Inventory> linkedList = new LinkedList<>();

		Connection connection = database.getConnection();
		Statement statement = connection.createStatement();

		String sql = String.format("Select * from %s", DbConstants.DB_TABLE_NAME);

		ResultSet resultSet = statement.executeQuery(sql);
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		int columnCount = resultSetMetaData.getColumnCount();

		while (resultSet.next()) {
			String[] inventory = new String[6];

			for (int i = 1; i <= columnCount; i++) {
				String data = resultSet.getString(i);
				inventory[i] = data;

			}
			linkedList.add(new Inventory(inventory[1], inventory[2], inventory[3], inventory[4], inventory[5]));
		}
		ShareableData.getInstance().setInventoryLinkedList(linkedList);
		log.info("Inventory List created and shared to singelton");
	}

}
