package examples.database;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import examples.database.util.DbUtil;

/**
 * Run 'DatabaseDemo' first to populate the database.
 * 
 * @author scirka
 * 
 */
@SuppressWarnings("serial")
public class FindGrade extends JFrame {
	private final Properties properties;
	private static Connection connection;

	/** Main method */
	public static void main(String[] args) {
		File file = new File(DbConstants.DB_PROPERTIES_FILENAME);
		if (!file.exists()) {
			showUsage();
			System.exit(-1);
		}

		try {
			new FindGrade(file).run();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void showUsage() {
		JOptionPane.showMessageDialog(null, String.format("Program cannot start because %s cannot be found.", DbConstants.DB_PROPERTIES_FILENAME));
	}

	/**
	 * Initialize the app
	 * 
	 * @throws Exception
	 */
	public FindGrade(File propertiesFile) throws Exception {
		properties = new Properties();
		properties.load(new FileInputStream(propertiesFile));
	}

	private void run() throws ClassNotFoundException, SQLException {
		// Initialize database connection and create a Statement object
		initializeDB();
		Statement statement = null;

		try {
			statement = connection.createStatement();
			showGrade(statement, "A44111110", "11111");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void initializeDB() throws ClassNotFoundException, SQLException {
		Class.forName(properties.getProperty(DbConstants.DB_DRIVER_KEY));
		System.out.println("Driver loaded");
		connection = DriverManager.getConnection(properties.getProperty(DbConstants.DB_URL_KEY), properties.getProperty(DbConstants.DB_USER_KEY),
				properties.getProperty(DbConstants.DB_PASSWORD_KEY));
		System.out.println("Database connected");
	}

	private void showGrade(Statement statement, String studentId, String courseId) throws SQLException {
		String sql = String.format(
				"select firstName, mi, lastName, title, grade from %s, %s, %s "
						+ "where %s.studentId = '%s' and %s.courseId = '%s' and %s.courseId = %s.courseId and %s.studentId = %s.studentId",
				DbConstants.STUDENT_TABLE_NAME, DbConstants.COURSE_TABLE_NAME, DbConstants.ENROLLMENT_TABLE_NAME, DbConstants.STUDENT_TABLE_NAME,
				studentId, DbConstants.ENROLLMENT_TABLE_NAME, courseId, DbConstants.ENROLLMENT_TABLE_NAME, DbConstants.COURSE_TABLE_NAME,
				DbConstants.ENROLLMENT_TABLE_NAME, DbConstants.STUDENT_TABLE_NAME);

		ResultSet resultSet = DbUtil.executeQuery(statement, sql);

		if (resultSet.next()) {
			String lastName = resultSet.getString(StudentTable.LAST_NAME.getName());
			String mi = resultSet.getString(StudentTable.MI.getName());
			String firstName = resultSet.getString(StudentTable.FIRST_NAME.getName());
			String title = resultSet.getString(4);
			String grade = resultSet.getString(5);

			// Display the results
			System.out.format("%s %s %s's grade in course \"%s\" is '%s'", firstName, mi, lastName, title, grade);
		} else {
			System.out.println("Not found");
		}
	}

}
