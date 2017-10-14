package A00980851.db.dao;

import A00980851.db.setup.DatabaseSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Name Japneet Johal A00 980 851 Project Name A00980851AssignmentTwo Class Name
 * Dao Date 2017-06-18
 */
public abstract class Dao {

	protected final DatabaseSetup database;
	private final String tableName;

	private final static Logger log = LogManager.getLogger();

	public Dao(DatabaseSetup database, String tableName) {
		this.database = database;
		this.tableName = tableName;
	}

	public abstract void create();

	public void create(String sql) throws SQLException {
		Statement statement = null;

		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			log.debug("Created this table sucessfully: " + sql);
		} finally {
			close(statement);

		}
	}

	public void add(String sql) throws SQLException {
		Statement statement = null;

		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			log.debug(sql);
			statement.executeUpdate(sql);
		} finally {
			close(statement);
		}
	}

	public void drop() throws SQLException {
		Statement statement = null;

		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			String sql = "drop table " + tableName;
			log.debug(sql);
			statement.executeUpdate(sql);
		} finally {
			close(statement);
		}
	}

	public void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
				statement = null;
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
	}

}
