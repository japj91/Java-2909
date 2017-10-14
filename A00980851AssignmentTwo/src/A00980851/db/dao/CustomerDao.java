package A00980851.db.dao;

import A00980851.data.Customer;
import A00980851.db.setup.DatabaseSetup;
import A00980851.db.setup.DbConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

/**
 * Name Japneet Johal A00 980 851 Project Name A00980851AssignmentTwo Class Name
 * CustomerDao Date 2017-06-28
 */
public class CustomerDao extends Dao {

	static final String TABLE_NAME = DbConstants.DB_TABLE_NAME;
	static Connection connection;
	private static final Logger log = LogManager.getLogger();

	/**
	 * constructor that takes a DB setup
	 *
	 * @param databaseSetup
	 */
	public CustomerDao(DatabaseSetup databaseSetup) {
		super(databaseSetup, TABLE_NAME);

	}

	/**
	 * takes a object and uses its properties to update the database
	 * 
	 * @param customer
	 * @throws SQLException
	 */
	public void update(Customer customer) throws SQLException {
		Connection connection = database.getConnection();
		Statement statement = connection.createStatement();

		String sql = String.format(
				"update %s set %s = '%s', %s = '%s', %s = '%s', %s = '%s', %s = '%s' WHERE %s = '%s' and %s = '%s'",
				DbConstants.DB_TABLE_NAME, "identifier", customer.getId(), "FirstName", customer.getFirstName(),
				"LastName", customer.getLastName(), "Street", customer.getStreet(), "postalCode",
				customer.getPostalCode(), "city", customer.getCity(), "phone", customer.getPhone(), "Email",
				customer.getEmail(), "joinDate", toTimestamp(customer.getJoinDate()));

		statement.executeUpdate(sql);
	}

	private Timestamp toTimestamp(LocalDate joinDate) {
		return Timestamp.valueOf(LocalDateTime.of(joinDate, LocalTime.now()));

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
	 * takes a list and each individual object is added to database
	 * 
	 * @param linkedList
	 * @throws SQLException
	 */
	public void add(LinkedList<Customer> linkedList) throws SQLException {
		Statement statement = null;

		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			int count = 0;
			for (Customer customer : linkedList) {
				String sql = String.format(
						" insert into %s values ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')", "Customer",
						customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getStreet(),
						customer.getPostalCode(), customer.getCity(), customer.getPhone(), customer.getEmail(),
						toTimestamp(customer.getJoinDate()));
				statement.executeUpdate(sql);
				count++;
			}
			log.info(String.format("DB ADDED %s customer entries", count));
		} catch (SQLException e) {
			log.error(e.getMessage());

		} finally {
			statement.close();
		}
		log.info("Customer data inserted sucessfully");
	}

	/**
	 * Drops a table with the tableName
	 * 
	 * @param dbTableName
	 * @throws SQLException
	 */
	public void drop(String dbTableName) throws SQLException {
		String tableName = String.format("Drop table %s ", dbTableName);
		connection = database.getConnection();
		Statement statement = connection.createStatement();

		if (DatabaseSetup.tableExists(dbTableName) == true) {
			statement.executeUpdate(tableName);
		}
	}

	@Override
	public void create() {
	}
}
