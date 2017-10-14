package examples.database.daodemo.dao;

import java.sql.SQLException;

import examples.database.daodemo.Database;
import examples.database.daodemo.DbConstants;

public class EnrollmentDao extends Dao {

	public static final String TABLE_NAME = DbConstants.ENROLLMENT_TABLE_NAME;

	public EnrollmentDao(Database database) {
		super(database, TABLE_NAME);
	}

	@Override
	public void create() throws SQLException {
		String createStatement = String.format(
				"create table %s(" //
						+ "studentId VARCHAR(9), " //
						+ "courseId VARCHAR(10), " //
						+ "dateRegistered date, " //
						+ "grade VARCHAR(1), " //
						+ "primary key (studentId, courseId), " //
						+ "foreign key (studentId) references %s, " //
						+ "foreign key (courseId) references %s)", //
				TABLE_NAME, StudentDao.TABLE_NAME, CourseDao.TABLE_NAME);
		super.create(createStatement);
	}
}