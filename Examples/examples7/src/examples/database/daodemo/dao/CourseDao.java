package examples.database.daodemo.dao;

import examples.database.daodemo.Database;
import examples.database.daodemo.DbConstants;
import examples.database.daodemo.data.Course;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CourseDao extends Dao {

	public static final String TABLE_NAME = DbConstants.COURSE_TABLE_NAME;

	public CourseDao(Database database) {
		super(database, TABLE_NAME);
	}

	@Override
	public void create() throws SQLException {
		String createStatement = String.format(
				"create table %s(courseId VARCHAR(10), subjectId VARCHAR(4), courseNumber VARCHAR(4), title VARCHAR(64), numOfCredits INTEGER, primary key (courseId) )",
				TABLE_NAME);
		super.create(createStatement);
	}

	public void add(Course course) throws SQLException {
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			String insertString = String.format("insert into %s values('%s', '%s', '%s', '%s', %d)", tableName, course.getCourseId(),
					course.getSubjectId(), course.getCourseNumber(), course.getTitle(), course.getNumOfCredits());
			System.out.println(insertString);
			statement.executeUpdate(insertString);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void update(Course course) throws SQLException {
		// TODO
	}

	public void delete(Course course) throws SQLException {
		// TODO
	}

}
