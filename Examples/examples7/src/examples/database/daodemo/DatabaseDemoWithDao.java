package examples.database.daodemo;

import examples.database.DbConstants;
import examples.database.Student;
import examples.database.daodemo.dao.CourseDao;
import examples.database.daodemo.dao.EnrollmentDao;
import examples.database.daodemo.dao.StudentDao;
import examples.database.daodemo.data.Course;
import examples.database.util.DbUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseDemoWithDao {

	private static final String LOG4J_CONFIG_FILENAME = "log4j2.xml";

	static {
		configureLogging();
	}

	private static final Logger LOG = LogManager.getLogger();
	private static Database database;

	private StudentDao studentDao;
	private CourseDao courseDao;
	private EnrollmentDao enrollmentDao;
	private final Properties dbProperties;
	private Connection connection;

	public static void main(String[] args) throws Exception {

		File dbPropertiesFile = new File(DbConstants.DB_PROPERTIES_FILENAME);
		if (!dbPropertiesFile.exists()) {
			showUsage();
			System.exit(-1);
		}

		try {
			new DatabaseDemoWithDao(dbPropertiesFile).run();
		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		} finally {
			database.shutdown();
		}
	}

	private static void configureLogging() {
		ConfigurationSource source;
		try {
			source = new ConfigurationSource(new FileInputStream(LOG4J_CONFIG_FILENAME));
			Configurator.initialize(null, source);

		} catch (IOException e) {
			System.out.println(String.format("Can't find the log4j logging configuration file %s.", LOG4J_CONFIG_FILENAME));
		}
	}

	private static void showUsage() {
		System.err.println("The database properties filename must be passed in the commandline.");
	}

	private DatabaseDemoWithDao(File dbPropertiesFile) throws IOException {
		dbProperties = new Properties();
		dbProperties.load(new FileReader(dbPropertiesFile));
		database = new Database(dbProperties);
	}

	private void run() throws ClassNotFoundException, SQLException {
		connect();

		try {
			dropTables();

			createTables();

			insertCourses();
			insertStudents();
			insertEnrollments();

			Student student = readStudent();
			student.setFirstName("Peter");
			update(student);

			delete(student);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			connection.close();
		}
	}

	private void connect() throws SQLException {
		connection = database.getConnection();
		studentDao = new StudentDao(database);
		courseDao = new CourseDao(database);
		enrollmentDao = new EnrollmentDao(database);
	}

	private void dropTables() throws SQLException {
		enrollmentDao.drop();
		studentDao.drop();
		courseDao.drop();
	}

	private void createTables() throws SQLException {
		courseDao.create();
		studentDao.create();
		enrollmentDao.create();
	}

	private void insertCourses() throws SQLException {
		try {
			courseDao.add(new Course("11111", "CSCI", "1301", "Introduction to Java I", 4));
			courseDao.add(new Course("11112", "CSCI", "1302", "Introduction to Java II", 3));
			courseDao.add(new Course("11113", "CSCI", "3720", "Database Systems", 3));
			courseDao.add(new Course("11114", "CSCI", "4750", "Rapid Java Application", 3));
			courseDao.add(new Course("11115", "MATH", "2750", "Calculus I", 5));
			courseDao.add(new Course("11116", "MATH", "3750", "Calculus II", 5));
			courseDao.add(new Course("11118", "ITEC", "1301", "Database Administration", 3));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

	private void insertStudents() throws SQLException {
		try {
			studentDao.add(new Student("A44111110", "Jacob", "R", "Smith", "9129219434", "1985-04-09", "99 Kingston Street", "31435", "BIOL"));
			studentDao.add(new Student("A44111111", "John", "K", "Stevenson", "9129219434", "1900-01-01", "100 Main Street", "31411", "BIOL"));
			studentDao.add(new Student("A44111112", "George", "K", "Smith", "9129213454", "1974-10-10", "1200 Abercorn St.", "31419", "CS"));
			studentDao.add(new Student("A44111113", "Frank", "E", "Jones", "9125919434", "1970-09-09", "100 Main Street", "31411", "BIOL"));
			studentDao.add(new Student("A44111114", "Jean", "K", "Smith", "9129219434", "1970-02-09", "100 Main Street", "31411", "CHEM"));
			studentDao.add(new Student("A44111115", "Josh", "R", "Woo", "7075989434", "1970-02-09", "555 Franklin St.", "31411", "CHEM"));
			studentDao.add(new Student("A44111116", "Josh", "R", "Smith", "9129219434", "1973-02-09", "100 Main Street", "31411", "BIOL"));
			studentDao.add(new Student("A44111117", "Joy", "P", "Kennedy", "9129229434", "1974-03-19", "103 Bay Street", "31412", "CS"));
			studentDao.add(new Student("A44111118", "Toni", "R", "Peterson", "9129229434", "1964-04-29", "103 Bay Street", "31412", "MATH"));
			studentDao.add(new Student("A44111119", "Patrick", "R", "Stoneman", "9129229434", "1969-04-29", "101 Washington St.", "31435", "MATH"));
			studentDao.add(new Student("A44111120", "Rick", "R", "Carter", "9125919434", "1986-04-09", "19 West Ford St.", "31411", "BIOL"));
		} catch (SQLException e) {
			e.printStackTrace();
			e.getMessage();
			System.out.println(e);
		}
	}

	private void insertEnrollments() throws SQLException {
		String DATA[] = { //
				"insert into %s values ('A44111110', '11111', '2004-09-30', 'A')", //
				"insert into %s values ('A44111110', '11112', '2005-11-23', 'B')", //
				"insert into %s values ('A44111110', '11113', '2014-02-28', 'C')", //
				"insert into %s values ('A44111111', '11111', '2008-07-03', 'D')", //
				"insert into %s values ('A44111111', '11112', '2009-08-09', 'F')", //
				"insert into %s values ('A44111111', '11113', '2011-06-14', 'A')", //
				"insert into %s values ('A44111112', '11114', '2011-04-20', 'B')", //
				"insert into %s values ('A44111112', '11115', '2007-12-10', 'C')", //
				"insert into %s values ('A44111112', '11116', '2009-05-26', null)", //
				"insert into %s values ('A44111113', '11111', '2003-01-05', null)", //
				"insert into %s values ('A44111113', '11113', '2012-03-17', null)", //
				"insert into %s values ('A44111114', '11115', '2015-03-07', null)", //
				"insert into %s values ('A44111115', '11115', '2016-05-21', null)", //
				"insert into %s values ('A44111115', '11116', '2014-06-25', null)", //
				"insert into %s values ('A44111116', '11111', '2012-09-24', null)", //
				"insert into %s values ('A44111117', '11111', '2011-12-11', null)", //
				"insert into %s values ('A44111118', '11111', '2013-10-12', null)", //
				"insert into %s values ('A44111118', '11112', '2009-03-02', null)", //
				"insert into %s values ('A44111118', '11113', '2008-02-15', null)" //
		};
		Statement statement = connection.createStatement();
		String sql;

		for (int i = 0; i < DATA.length; i++) {
			sql = String.format(DATA[i], DbConstants.ENROLLMENT_TABLE_NAME);
			try {
				DbUtil.executeUpdate(statement, sql);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e);
			}
		}

		if (statement != null) {
			statement.close();
		}
	}

	private Student readStudent() {
		Student student = null;
		try {
			student = studentDao.readByStudentId("A44111120");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return student;
	}

	private void update(Student student) {
		try {
			studentDao.update(student);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
		}
	}

	private void delete(Student student) {
		try {
			studentDao.delete(student);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
		}
	}

}
