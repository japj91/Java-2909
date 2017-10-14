package examples.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FindGradeUsingPreparedStatement extends JFrame {

	public static final String TABLE_NAME = "jdbc_test";
	public static final String SSN = "ssn";
	public static final String FIRST_NAME = "firstName";
	public static final String MI = "mi";
	public static final String LAST_NAME = "lastName";
	public static final String PHONE = "phone";
	public static final String BIRTHDATE = "birthDate";
	public static final String STREET = "street";
	public static final String ZIPCODE = "zipCode";
	public static final String DEPT_ID = "deptID";
	public static final int COLUMNS = 9;
	private static Connection connection;
	private final Properties properties;

	// PreparedStatement for executing queries
	private PreparedStatement preparedStatement;

	/*
	 * The follow data is held in the Student table. Use when searching records.
	 * Fields = ssn, firstName, mi, lastName, birthDate, street, phone, zipCode, deptId
	 * A44111110 Jacob R Smith 9129219434 1985-04-09 99 Kingston Street 31435 BIOL
	 * A44111111 John K Stevenson 9129219434 null 100 Main Street 31411 BIOL
	 * A44111112 George K Smith 9129213454 1974-10-10 1200 Abercorn St. 31419 CS
	 * A44111113 Frank E Jones 9125919434 1970-09-09 100 Main Street 31411 BIOL
	 * A44111114 Jean K Smith 9129219434 1970-02-09 100 Main Street 31411 CHEM
	 * A44111115 Josh R Wu 7075989434 1970-02-09 555 Franklin St. 31411 CHEM
	 * A44111116 Josh R Smith 9129219434 1973-02-09 100 Main Street 31411 BIOL
	 * A44111117 Joy P Kennedy 9129229434 1974-03-19 103 Bay Street 31412 CS
	 * A44111118 Toni R Peterson 9129229434 1964-04-29 103 Bay Street 31412 MATH
	 * A44111119 Patrick R Stoneman 9129229434 1969-04-29 101 Washington St. 31435 MATH
	 * A44111120 Rick R Dodge 9125919434 1986-04-09 19 West Ford St. 31411 BIOL
	 */

	/**
	 * Main method
	 * 
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws IOException, SQLException {
		new FindGradeUsingPreparedStatement().run();
	}

	public FindGradeUsingPreparedStatement() throws IOException {
		File file = new File(DbConstants.DB_PROPERTIES_FILENAME);
		if (!file.exists()) {
			System.exit(-1);
		}

		properties = new Properties();
		properties.load(new FileInputStream(file));
	}

	private void run() throws SQLException {
		// Initialize database connection and create a Statement object
		initializeDB();

		dumpStudents();

		showBirthDate("Smith", "CS");
	}

	private void initializeDB() {
		try {
			// Load the JDBC driver
			Class.forName(properties.getProperty(DbConstants.DB_DRIVER_KEY));
			System.out.println("Driver loaded");

			// Establish a connection
			connection = DriverManager.getConnection( //
					properties.getProperty(DbConstants.DB_URL_KEY), //
					properties.getProperty(DbConstants.DB_USER_KEY), //
					properties.getProperty(DbConstants.DB_PASSWORD_KEY));
			System.out.println("Database connected");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}
	}

	private void dumpStudents() throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(String.format("select * from %s", DbConstants.STUDENT_TABLE_NAME));

		// Iterate through the result and print the student names
		while (resultSet.next()) {
			for (int i = 1; i <= COLUMNS; i++) {
				System.out.format("%-18s\t", resultSet.getObject(i));
			}

			System.out.println();
		}

		statement.close();
	}

	private void showBirthDate(String lastName, String deptId) {
		try {
			String queryString = String.format("select firstName, mi, " + "lastName, birthDate from %s where lastName = ? and deptId = ?",
					DbConstants.STUDENT_TABLE_NAME);
			System.out.println(queryString);
			preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setString(1, lastName);
			preparedStatement.setString(2, deptId);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String firstName = resultSet.getString(StudentTable.FIRST_NAME.getName());
				String mi = resultSet.getString(StudentTable.MI.getName());
				Date birthDate = resultSet.getDate(StudentTable.BIRTHDATE.getName());

				// Display the results
				System.out.format("%s %s %s's birthday is %s.", firstName, mi, lastName, birthDate.toString());
			} else {
				System.out.println("Not found");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}
	}

}
