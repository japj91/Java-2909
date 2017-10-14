package examples.database;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import examples.database.util.DbUtil;

public class SqlServerJdbcTest {

	private static Properties properties;

	public static void main(String[] args) throws Exception {
		File file = new File(DbConstants.DB_PROPERTIES_FILENAME);
		if (!file.exists()) {
			System.exit(-1);
		}

		properties = new Properties();
		properties.load(new FileInputStream(file));
		
		// Load the JDBC driver
		Class.forName(properties.getProperty(DbConstants.DB_DRIVER_KEY));
		System.out.println("Driver loaded");

		// Establish a connection
		Connection connection = DriverManager.getConnection(properties.getProperty(DbConstants.DB_URL_KEY), 
				properties.getProperty(DbConstants.DB_USER_KEY), properties.getProperty(DbConstants.DB_PASSWORD_KEY));
		System.out.println("Database connected");

		// Create a statement
		Statement statement = connection.createStatement();

		// Execute a statement
		String sql = String.format("select firstName, mi, lastName, birthDate from %s where lastName = 'Smith'", DbConstants.STUDENT_TABLE_NAME);
		DbUtil.executeQuery(statement, sql);
		ResultSet resultSet = statement.executeQuery(sql);

		// Iterate through the result and print the student names
		while (resultSet.next()) {
			System.out.println(resultSet.getString("firstName") + "\t" + resultSet.getString("mi") + "\t"
			        + resultSet.getString("lastName") + "\t" + resultSet.getDate("birthDate"));
		}
		
		// Close the connection
		connection.close();
	}
}
