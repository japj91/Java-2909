package examples.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DumpSqlServerTables {

	public static final String DRIVER = "net.sourceforge.jtds.jdbc.Driver";
	public static final String URL = "jdbc:jtds:sqlserver";
	public static final String USER = "javastudent";
	public static final String PASSWORD = "compjava";
	public static final String NAME = "jspweb";
	private static boolean showColumns;

	public static void main(String[] args) throws Exception {

		if (args.length == 1) {
			if ("showColumns".equals(args[0])) {
				showColumns = true;
			}
		}

		// Load the JDBC driver
		Class.forName(DRIVER);
		System.out.println("Driver loaded");

		// Establish a connection
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

		// Create a statement
		Statement statement = connection.createStatement();

		// Execute a statement
		ResultSet resultSet = statement.executeQuery("SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE'");

		List<String> tableNames = new ArrayList<>();

		while (resultSet.next()) {
			tableNames.add(resultSet.getString(3));
		}

		resultSet.close();

		for (String tableName : tableNames) {
			System.out.println(tableName);
			try {
				resultSet = statement.executeQuery(String.format("select * from %s", tableName));
				ResultSetMetaData rsmd = resultSet.getMetaData();
				int numColumns = rsmd.getColumnCount();

				if (showColumns) {
					for (int i = 1; i < numColumns + 1; i++) {
						String columnName = rsmd.getColumnName(i);
						System.out.println("\t" + columnName);
					}
				}
			} catch (SQLException e) {
				System.out.println("Warning " + e.getMessage());
			} finally {
				resultSet.close();
			}
		}

		for (String tableName : tableNames) {
			System.out.println(tableName);
		}
		// Close the connection
		connection.close();
	}
}
