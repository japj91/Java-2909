package examples.database;

import examples.database.util.DbUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

public class DatabaseDemo {

	private static final String LOG4J_CONFIG_FILENAME = "log4j2.xml";

	static {
		configureLogging();
	}

	private static final Logger LOG = LogManager.getLogger();

	private final Properties properties;
	private static Connection connection;

	public static void main(String[] args) {
		File dbPropertiesFile = new File(DbConstants.DB_PROPERTIES_FILENAME);
		if (!dbPropertiesFile.exists()) {
			showUsage();
			System.exit(-1);
		}

		try {
			new DatabaseDemo(dbPropertiesFile).run();
		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		} finally {
			shutdown();
		}
	}

	private static void configureLogging() {
		ConfigurationSource source;
		try {
			source = new ConfigurationSource(new FileInputStream(LOG4J_CONFIG_FILENAME));
			Configurator.initialize(null, source);
		} catch (IOException e) {
			System.out.println(
					String.format("Can't find the log4j logging configuration file %s.", LOG4J_CONFIG_FILENAME));
		}
	}

	private static void shutdown() {
		LOG.info("Shutting down");
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOG.error(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	private static void showUsage() {
		System.err.println(
				String.format("Program cannot start because %s cannot be found.", DbConstants.DB_PROPERTIES_FILENAME));
	}

	private DatabaseDemo(File file) throws IOException {
		properties = new Properties();
		properties.load(new FileInputStream(file));
	}

	private void run() throws ClassNotFoundException, SQLException {
		LOG.info("Running");

		Class.forName(properties.getProperty(DbConstants.DB_DRIVER_KEY));
		LOG.info("Driver loaded");

		connect();

		Statement statement = connection.createStatement();

		try {
			dropTables(statement);

			createTables(statement);

			insertCourses();
			insertStudents();
			insertEnrollments();

			updateStudent();
			updateStudentUsingPreparedStatement();

			getStudent("A44111115");
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
		} finally {
			connection.close();
		}
	}

	private void connect() throws SQLException, ClassNotFoundException {
		connection = DriverManager.getConnection( 
				properties.getProperty(DbConstants.DB_URL_KEY), 
				properties.getProperty(DbConstants.DB_USER_KEY), 
				properties.getProperty(DbConstants.DB_PASSWORD_KEY));
		LOG.info("Database connected");
	}

	private void dropTables(Statement statement) throws SQLException {
		if (DbUtil.tableExists(connection, DbConstants.ENROLLMENT_TABLE_NAME)) {
			statement.executeUpdate(String.format("drop table %s", DbConstants.ENROLLMENT_TABLE_NAME));
		}

		if (DbUtil.tableExists(connection, DbConstants.STUDENT_TABLE_NAME)) {
			statement.executeUpdate(String.format("drop table %s", DbConstants.STUDENT_TABLE_NAME));
		}

		if (DbUtil.tableExists(connection, DbConstants.COURSE_TABLE_NAME)) {
			statement.executeUpdate(String.format("drop table %s", DbConstants.COURSE_TABLE_NAME));
		}
	}

	private void createTables(Statement statement) throws SQLException {
		String sql = String.format(
				"create table %s(" + // Student table
						"studentId VARCHAR(9), " + "firstName VARCHAR(10), " + "mi VARCHAR(1), "
						+ "lastName VARCHAR(10), " + "phone VARCHAR(10), " + "birthDate DATE, " + "street VARCHAR(20), "
						+ "zipCode VARCHAR(10), " + "deptID VARCHAR(4), " + "primary key (studentId) )",
				DbConstants.STUDENT_TABLE_NAME);
		DbUtil.executeUpdate(statement, sql);
		sql = String.format(
				"create table %s(" + // Course table
						"courseId VARCHAR(10), " + "subjectId VARCHAR(4), " + "courseNumber VARCHAR(4), "
						+ "title VARCHAR(64), " + "numOfCredits INTEGER, " + "primary key (courseId) )",
				DbConstants.COURSE_TABLE_NAME);
		DbUtil.executeUpdate(statement, sql);
		sql = String.format(
				"create table %s(" + // Enrollment table
						"studentId VARCHAR(9), " + "courseId VARCHAR(10), " + "dateRegistered date, "
						+ "grade VARCHAR(1), " + "primary key (studentId, courseId), "
						+ "foreign key (studentId) references %s, " + "foreign key (courseId) references %s )",
				DbConstants.ENROLLMENT_TABLE_NAME, DbConstants.STUDENT_TABLE_NAME, DbConstants.COURSE_TABLE_NAME);
		DbUtil.executeUpdate(statement, sql);
	}

	private void insertCourses() throws SQLException {
		String DATA[] = {
				// courseId, subjectId, courseNumber, title, numOfCredits
				"'11111', 'COMP', '2613', 'Introduction to Java I', 4",
				"'11112', 'COMP', '3613', 'Introduction to Java II', 3",
				"'11113', 'COMP', '3720', 'Database Systems', 3",
				"'11114', 'COMP', '4750', 'Rapid Java Application', 3", "'11115', 'MATH', '2750', 'Calculus I', 5",
				"'11116', 'MATH', '3750', 'Calculus II', 5", "'11117', 'EDUC', '1344', 'Reading', 3",
				"'11118', 'ITEC', '1301', 'Database Administration', 3" };
		Statement statement = connection.createStatement();
		String sql;

		try {
			for (int i = 0; i < DATA.length; i++) {
				sql = String.format("insert into %s values(%s)", DbConstants.COURSE_TABLE_NAME, DATA[i]);
				DbUtil.executeUpdate(statement, sql);
			}
		} catch (SQLException e) {
			LOG.error(e);
			e.printStackTrace();
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}

	private void insertStudents() throws SQLException {
		// studentId firstName mi lastName phone birthDate street zipCode deptID
		String DATA[] = {
				"'A44111110', 'Jacob', 'R', 'Smith', '9129219434', '1985-04-09', '99 Kingston Street', '31435', 'BIOL'",
				"'A44111111', 'John', 'K', 'Stevenson', '9129219434', null, '100 Main Street', '31411', 'BIOL'",
				"'A44111112', 'George', 'K', 'Smith', '9129213454', '1974-10-10', '1200 Abercorn St.', '31419', 'CS'",
				"'A44111113', 'Frank', 'E', 'Jones', '9125919434', '1970-09-09', '100 Main Street', '31411', 'BIOL'",
				"'A44111114', 'Jean', 'K', 'Smith', '9129219434', '1970-02-09', '100 Main Street', '31411', 'CHEM'",
				"'A44111115', 'Josh', 'R', 'Woo', '7075989434', '1970-02-09', '555 Franklin St.', '31411', 'CHEM'",
				"'A44111116', 'Josh', 'R', 'Smith', '9129219434', '1973-02-09', '100 Main Street', '31411', 'BIOL'",
				"'A44111117', 'Joy', 'P', 'Kennedy', '9129229434', '1974-03-19', '103 Bay Street', '31412', 'CS'",
				"'A44111118', 'Toni', 'R', 'Peterson', '9129229434', '1964-04-29', '103 Bay Street', '31412', 'MATH'",
				"'A44111119', 'Patrick', 'R', 'Stoneman', '9129229434', '1969-04-29', '101 Washington St.', '31435', 'MATH'",
				"'A44111120', 'Rick', 'R', 'Carter', '9125919434', '1986-04-09', '19 West Ford St.', '31411', 'BIOL'" };
		Statement statement = connection.createStatement();
		String sql;

		try {
			for (int i = 0; i < DATA.length; i++) {
				sql = String.format("insert into %s values(%s)", DbConstants.STUDENT_TABLE_NAME, DATA[i]);
				DbUtil.executeUpdate(statement, sql);
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}

	private void insertEnrollments() throws SQLException {
		// studentId, courseId, dateRegistered , grade

		String DATA[][] = { 
				{ "A44111110", "11111", "2004-03-19", "A" }, 
				{ "A44111110", "11112", "2004-03-19", "B" }, 
				{ "A44111110", "11113", "2004-03-19", "C" }, 
				{ "A44111111", "11111", "2004-03-19", "D" }, 
				{ "A44111111", "11112", "2004-03-19", "F" }, 
				{ "A44111111", "11113", "2004-03-19", "A" }, 
				{ "A44111112", "11114", "2004-03-19", "B" }, 
				{ "A44111112", "11115", "2004-03-19", "C" }, 
				{ "A44111112", "11116", "2004-03-19", "C" }, 
				{ "A44111113", "11111", "2004-03-19", "B" }, 
				{ "A44111113", "11113", "2004-03-19", "C" }, 
				{ "A44111114", "11115", "2004-03-19", "C" }, 
				{ "A44111115", "11115", "2004-03-19", "C" }, 
				{ "A44111115", "11116", "2004-03-19", "A" }, 
				{ "A44111116", "11111", "2004-03-19", "C" }, 
				{ "A44111117", "11111", "2004-03-19", "C" }, 
				{ "A44111118", "11111", "2004-03-19", "A" }, 
				{ "A44111118", "11112", "2004-03-19", "C" }, 
				{ "A44111118", "11113", "2004-03-19", "B" } 
		};
		String sql = String.format("insert into %s values (?, ?, ?, ?)", DbConstants.ENROLLMENT_TABLE_NAME);

		PreparedStatement statement = null;
		try {
			for (int i = 0; i < DATA.length; i++) {
				String[] values = DATA[i];
				statement = connection.prepareStatement(sql);
				DbUtil.execute(statement, values[0], values[1], values[2], values[3]);
			}
		} catch (SQLException e) {
			LOG.error(e);
			e.printStackTrace();
		} finally {
			DbUtil.close(statement);
		}
	}

	private void updateStudent() {
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String sql = String.format("update %s set %s='Dodge' where %s='A44111120'", 
					DbConstants.STUDENT_TABLE_NAME, 
					StudentTable.LAST_NAME.getName(), 
					StudentTable.STUDENT_ID.getName());
			int rowcount = DbUtil.executeUpdate(statement, sql);
			System.out.format("Updated %d row(s)%n", rowcount);
		} catch (SQLException e) {
			LOG.error(e);
			e.printStackTrace();
		} finally {
			DbUtil.close(statement);
		}
	}

	private void updateStudentUsingPreparedStatement() {
		PreparedStatement statement = null;
		try {
			String sql = String.format("update %s set lastName=? where studentId=?", DbConstants.STUDENT_TABLE_NAME);
			statement = connection.prepareStatement(sql);
			DbUtil.execute(statement, "Wu", "A44111115");
		} catch (SQLException e) {
			LOG.error(e);
			e.printStackTrace();
		} finally {
			DbUtil.close(statement);
		}
	}

	private void getStudent(String studentId) {
		PreparedStatement statement = null;
		try {
			String sql = String.format("select * FROM %s WHERE %s = ?", DbConstants.STUDENT_TABLE_NAME,
					StudentTable.STUDENT_ID.getName());
			ResultSet results = DbUtil.executeQuery(connection, sql, studentId);
			while (results.next()) {
				System.out.format("%s %s %s %s %s %s %s %s %s%n", 
						results.getString(StudentTable.STUDENT_ID.getName()), 
						results.getString(StudentTable.FIRST_NAME.getName()), 
						results.getString(StudentTable.MI.getName()), 
						results.getString(StudentTable.LAST_NAME.getName()), 
						results.getString(StudentTable.PHONE.getName()), 
						results.getString(StudentTable.BIRTHDATE.getName()), 
						results.getString(StudentTable.STREET.getName()), 
						results.getString(StudentTable.ZIP_CODE.getName()), 
						results.getString(StudentTable.DEPT_ID.getName()) 
				);
			}
		} catch (SQLException e) {
			LOG.error(e);
			e.printStackTrace();
		} finally {
			DbUtil.close(statement);
		}
	}

	public static Date toDate(LocalDate date) {
		return Date.valueOf(date);
	}

	public static LocalDate toLocalDate(Date date) {
		return date.toLocalDate();
	}

}
