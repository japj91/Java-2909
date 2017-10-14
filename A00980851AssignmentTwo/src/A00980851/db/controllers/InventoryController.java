package A00980851.db.controllers;

import A00980851.data.Inventory;
import A00980851.db.setup.DatabaseSetup;
import A00980851.db.setup.DbConstants;
import A00980851.db.dao.InventoryDao;
import A00980851.util.ApplicationException;
import A00980851.util.ShareableData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

/**
 *
 * Name Japneet Johal A00 980 851 Project Name A00980851AssignmentTwo Class Name
 * InventoryController Date 2017-06-21
 */
public class InventoryController {

	private static DatabaseSetup databaseSetup;
	private Properties properties;

	private Connection connection;
	public LinkedList<Inventory> inventoriesLinkList;

	InventoryDao inventoryDao;

	private static final Logger logger = LogManager.getLogger();

	/**
	 * driver of the class
	 * @throws ApplicationException
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void load() throws ApplicationException, SQLException, IOException, ClassNotFoundException {

		File DbPropFile = new File(DbConstants.DB_PROPERTIES_FILENAME);
		if (!DbPropFile.exists()) {
			logger.error(String.format("%s file does not exist", DbConstants.DB_DRIVER_KEY));
			throw new ApplicationException(String.format("%s file does not exist", DbConstants.DB_DRIVER_KEY));
		}
		setUpDB(DbPropFile);
		run();
	}

	/**
	 * runs the methods that are known as CRUD
	 */
	private void run() {
		try {
			connect();
			if (!databaseSetup.tableExists(DbConstants.DB_TABLE_NAME)) {
				dropTables(DbConstants.DB_TABLE_NAME);
				createTable();
				addData();
			}
			getInventoryList();
			databaseSetup.shutdown();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * creates table
	 * @throws SQLException
	 */
	private void createTable() throws SQLException {
		String sql = String.format(
				"Create table %s (identifier varchar(40), description varchar(45), "
						+ "partNumber varchar(45), price varchar(40), quantity varchar(40))",
				DbConstants.DB_TABLE_NAME);
		inventoryDao.create(sql);
	}

	/**
	 * updates the DB
	 * @param inventory
	 * @throws SQLException
	 */
	public void update(Inventory inventory) throws SQLException {
		inventoryDao = new InventoryDao(databaseSetup);
		inventoryDao.update(inventory);
	}

	/**
	 * gets a updated list for the GUI
	 * @throws SQLException
	 */
	public void getInventoryList() throws SQLException {
		inventoryDao = new InventoryDao(databaseSetup);
		inventoryDao.getList();
	}

	/**
	 * add data to the table
	 * @throws SQLException
	 */
	private void addData() throws SQLException {
		this.inventoriesLinkList = ShareableData.getInstance().getInventoryLinkedList();
		inventoryDao.add(inventoriesLinkList);
	}

	/**
	 * drops tables with a table name
	 * @param dbTableName
	 * @throws SQLException
	 */
	private void dropTables(String dbTableName) throws SQLException {
		inventoryDao = new InventoryDao(databaseSetup);
		inventoryDao.drop(dbTableName);
	}

	/**
	 * connection
	 * @throws SQLException
	 */
	private void connect() throws SQLException {
		this.connection = databaseSetup.getConnection();
	}

	/**
	 * setting up the DB
	 * @param DbPropFile
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void setUpDB(File DbPropFile) throws IOException, ClassNotFoundException, SQLException {
		properties = new Properties();
		properties.load(new FileReader(DbPropFile));
		databaseSetup = new DatabaseSetup(properties);
	}

	/**
	 * serach for a indivudal user
	 * @param model
	 * @param partName
	 * @return
	 * @throws SQLException
	 */
	public Inventory search(String model, String partName) throws SQLException {
		connect();
		inventoryDao = new InventoryDao(databaseSetup);
		return inventoryDao.select(model, partName);
	}

}
