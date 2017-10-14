package examples.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import examples.database.util.DbUtil;

/**
 * Run 'DatabaseDemo' first to populate the database.
 * 
 * @author scirka
 * 
 */
public class SelectTest {

	private final Properties properties;
	private static Connection connection;

	public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
		File file = new File(DbConstants.DB_PROPERTIES_FILENAME);
		if (!file.exists()) {
			showUsage();
			System.exit(-1);
		}

		try {
			new SelectTest(file).run();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	private SelectTest(File file) throws FileNotFoundException, IOException {
		properties = new Properties();
		properties.load(new FileInputStream(file));
	}

	private void run() throws ClassNotFoundException, SQLException {
		connect();

		// Create a statement
		Statement statement = connection.createStatement();

		// Execute a statement
		ResultSet resultSet = DbUtil.executeQuery(statement,
				String.format("select firstName, mi, lastName from %s where lastName = 'Smith'", DbConstants.STUDENT_TABLE_NAME));

		// Iterate through the result and print the student names
		while (resultSet.next()) {
			System.out.println(resultSet.getString(StudentTable.FIRST_NAME.getName()) + "\t" + //
					resultSet.getString(StudentTable.MI.getName()) + "\t" + //
					resultSet.getString(StudentTable.LAST_NAME.getName()));
		}

		// Close the connection
		connection.close();
	}

	private void connect() throws ClassNotFoundException, SQLException {
		Class.forName(properties.getProperty(DbConstants.DB_DRIVER_KEY));
		System.out.println("Driver loaded");
		connection = DriverManager.getConnection( //
				properties.getProperty(DbConstants.DB_URL_KEY), //
				properties.getProperty(DbConstants.DB_USER_KEY), //
				properties.getProperty(DbConstants.DB_PASSWORD_KEY));
		System.out.println("Database connected");
	}

	private static void showUsage() {
		System.err.println(String.format("Program cannot start because %s cannot be found.", DbConstants.DB_PROPERTIES_FILENAME));
	}

}
