package A00980851.db.controllers;

import A00980851.data.Customer;
import A00980851.db.dao.CustomerDao;
import A00980851.db.setup.DatabaseSetup;
import A00980851.db.setup.DbConstants;
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
 * Name Japneet Johal A00 980 851 Project Name A00980851AssignmentTwo Class Name
 * CustomerController Date 2017-06-28
 */
public class CustomerController {
	private static DatabaseSetup databaseSetup;
	private Properties properties;

	private Connection connection;
	public LinkedList<Customer> customerLinkedList;

	CustomerDao customerDao;

	private static final Logger logger = LogManager.getLogger();

	/**
	 * driver of the class
	 * @throws ApplicationException
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void load() throws SQLException, IOException, ApplicationException, ClassNotFoundException {
		File DbPropFile = new File(DbConstants.DB_PROPERTIES_FILENAME);
		if (!DbPropFile.exists()) {
			logger.error("Db prop file not found");
		}
		setUpDb(DbPropFile);
		run();

	}
	/**
	 * runs the methods that are known as CRUD
	 */
	private void run() throws SQLException {
		try {
			connect();
			if (!databaseSetup.tableExists("Customer")) {
				logger.debug("Their was no previous table named Customer table was created and populated");
				dropTables("Customers");
				createTable();
				addData();

			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}


	/**
	 * creates table
	 * @throws SQLException
	 */
	private void createTable() {
		String sql = String.format(
				"Create table %s (identifier varchar(40), firstName varchar(45), "
						+ "lastName varchar(45), street varchar(40), postalCode varchar(40), city varchar(50), phone varchar(50), email varchar(100), joinDate varchar(100))",
				"Customer");
		try {
			customerDao.create(sql);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}
	/**
	 * updates the DB
	 * @param customer
	 * @throws SQLException
	 */
	public void update(Customer customer) throws SQLException {
		customerDao = new CustomerDao(databaseSetup);
		customerDao.update(customer);
	}
	/**
	 * add data to the table
	 * @throws SQLException
	 */
	private void addData() throws SQLException {
		this.customerLinkedList = ShareableData.getInstance().getCustomersList();
		customerDao.add(customerLinkedList);
	}
	/**
	 * drops tables with a table name
	 * @param dbTableName
	 * @throws SQLException
	 */

	private void dropTables(String dbTableName) throws SQLException {
		customerDao = new CustomerDao(databaseSetup);
		customerDao.drop(dbTableName);
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
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void setUpDb(File dbPropFile) throws SQLException, ClassNotFoundException, IOException {
		properties = new Properties();
		properties.load(new FileReader(dbPropFile));
		databaseSetup = new DatabaseSetup(properties);
	}

}
