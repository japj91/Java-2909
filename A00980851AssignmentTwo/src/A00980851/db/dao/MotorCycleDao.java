package A00980851.db.dao;

import A00980851.data.Motorcycle;
import A00980851.db.setup.DatabaseSetup;
import A00980851.db.setup.DbConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 * Name Japneet Johal A00 980 851 Project Name A00980851AssignmentTwo Class Name
 * MotorCycleDao Date 2017-06-29
 */
public class MotorCycleDao extends Dao {
	static final String TABLE_NAME = "Motorcycle";
	static Connection connection;
	private static final Logger log = LogManager.getLogger();

	/**
	 * constructor that takes a DB setup
	 *
	 * @param database
	 */
	public MotorCycleDao(DatabaseSetup database) {
		super(database, TABLE_NAME);
	}
	/**
	 * takes a object and uses its properties to update the database
	 *
	 * @param motorcycle
	 * @throws SQLException
	 */
	public void update(Motorcycle motorcycle) throws SQLException {
		Connection connection = database.getConnection();
		Statement statement = connection.createStatement();

		String sql = String.format(
				"update %s set %s = '%s', %s = '%s', %s = '%s', %s = '%s', %s = '%s' WHERE %s = '%s' and %s = '%s'",
				DbConstants.DB_TABLE_NAME, "id", motorcycle.getMotorcycle_ID(), "make", motorcycle.getMake(), "model",
				motorcycle.getModel(), "years", motorcycle.getYear(), "serialNumber", motorcycle.getSerialNumber(),
				"mileage", motorcycle.getMileage());

		statement.executeUpdate(sql);
		log.debug("Motorcycle Updated");
	}

	/**
	 * takes a list and each individual object is added to database
	 *
	 * @param linkedList
	 * @throws SQLException
	 */
	public void add(LinkedList<Motorcycle> linkedList) {
		Statement statement = null;

		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			int count = 0;
			for (Motorcycle motorcycle : linkedList) {
				String sql = String.format(" insert into %s values ('%s', '%s', '%s', '%s', '%s', '%s')", "Motorcycle",
						motorcycle.getMotorcycle_ID(), motorcycle.getMake(), motorcycle.getModel(),
						motorcycle.getYear(), motorcycle.getSerialNumber(), motorcycle.getMileage());
				statement.executeUpdate(sql);
				count++;

			}
			log.info(String.format("DB ADDED %s MOTORCYCLE ENTRIES", count));
		} catch (SQLException e) {
			log.error(e.getCause());
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				log.error(e.getCause());
			}
		}
		log.info("Motorcycle data inserted sucessfully");
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
	 * Drops a table with the tableName
	 *
	 * @param sql
	 * @throws SQLException
	 */
	public void drop(String sql) throws SQLException {
		String tableName = String.format("Drop table %s ", sql);
		connection = database.getConnection();
		Statement statement = connection.createStatement();

		if (DatabaseSetup.tableExists(sql) == true) {
			statement.executeUpdate(tableName);
		}

	}

	@Override
	public void create() {

	}
}
