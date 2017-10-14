package A00980851.db.setup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Properties;

/**
 * Name Japneet Johal A00 980 851 Project Name A00980851AssignmentTwo Class Name
 * DatabaseSetup Date 2017-06-18
 */

public class DatabaseSetup {

	public static final String DB_DRIVER_KEY = "db.driver";
	public static final String DB_URL_KEY = "db.url";
	public static final String DB_USER_KEY = "db.user";
	public static final String DB_PASSWORD_KEY = "db.password";

	private static Logger LOG = LogManager.getLogger();

	private static Connection connection;
	private final Properties properties;

	public DatabaseSetup(Properties properties) throws FileNotFoundException, ClassNotFoundException, SQLException {
		this.properties = properties;
	}

	public Connection getConnection() throws SQLException {
		if (connection != null) {
			return connection;
		}
		try {
			connect();
		} catch (ClassNotFoundException e) {
			LOG.error(e.getMessage());
		}
		return connection;
	}

	/**
	 * You use the driver, username and password to connect to database
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	protected void connect() throws ClassNotFoundException, SQLException {

		Class.forName(properties.getProperty(DB_DRIVER_KEY));
		connection = DriverManager.getConnection(properties.getProperty(DB_URL_KEY));


	}

	public void shutdown() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				LOG.error(e.getMessage());
			}
		}
	}

	/**
	 * returns boolean if table exists or not
	 * 
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public static boolean tableExists(String tableName) throws SQLException {
		DatabaseMetaData databaseMetaData = connection.getMetaData();
		ResultSet resultSet = null;
		String rsTablename;

		try {
			resultSet = databaseMetaData.getTables(connection.getCatalog(), "%", "%", null);
			while (resultSet.next()) {
				rsTablename = resultSet.getString("Table_name");
				if (rsTablename.equalsIgnoreCase(tableName)) {
					return true;
				}
			}
		} finally {
			resultSet.close();
		}
		return false;
	}
}
