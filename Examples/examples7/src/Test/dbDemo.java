package Test;

import examples.database.DbConstants;
import examples.database.util.DbUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Name            Japneet Johal A00 980 851
 * Project Name    examples7
 * Class  Name     dbDemo
 * Date            2017-06-03
 */

public class dbDemo {
    private final Properties properties;
    private static Connection connection;



    public static void main(String[] args) {
        File dbProperties = new File(DbConstants.DB_PROPERTIES_FILENAME);

        if (!dbProperties.exists()){
            showUsage();
            System.exit(-1);
        }
        try{
            new dbDemo(dbProperties).run();
        }catch (Exception e){
            System.out.println("line 37");
        }

    }

    private void run()  {

        try {
            Class.forName(properties.getProperty(DbConstants.DB_DRIVER_KEY));

            connect();
            Statement statement = connection.createStatement();

            dropTables(statement);
            createTable(statement);
            insertInto();
            query();
        }
         catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dropTables(Statement statement) throws SQLException {
        if (DbUtil.tableExists(connection, "jap")) {
            statement.executeUpdate(String.format("drop table %s","jap"));
        }
    }

    private void query() throws SQLException{
        String sql = "Select * from jap";

        Statement statement = connection.createStatement();
        ResultSet resultSet = dbutil.executeQuery(statement,sql);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columns = resultSetMetaData.getColumnCount();

        String columnName = resultSetMetaData.getColumnName(1);
        String columnNamea = resultSetMetaData.getColumnName(2);
        System.out.printf("%s  %s\n",columnName,columnNamea);

        while (resultSet.next()){
            for (int i = 1; i<= columns; i++){
                String data = resultSet.getString(i);
                System.out.printf("%s ",data);
            }
            System.out.println();
        }
    }

    private void insertInto() throws SQLException {
        String[] date = {"'A00980851', 'Jap Johal'"," 'A009811111', 'Avneet'"};

        String sql;
        Statement statement = connection.createStatement();
        try {
            for (String string : date) {
                sql = String.format("insert into jap values (%s)", string);
                dbutil.executeUpdate(statement, sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    private void connect()throws SQLException,ClassNotFoundException {
        connection = DriverManager.getConnection(
                properties.getProperty(DbConstants.DB_URL_KEY),
                properties.getProperty(DbConstants.DB_USER_KEY),
                properties.getProperty(DbConstants.DB_PASSWORD_KEY));

    }

    private void createTable(Statement statement) throws SQLException{
        String sql =
                String.format("Create table jap(" +
                    "studentID varchar(10)," +
                        "name varchar(10))"
                        );
        dbutil.execute(statement,sql);
    }

    private static void showUsage() {
        System.err.println(String.format("Program cannot start becuase %s cannot be found",DbConstants.DB_PROPERTIES_FILENAME));
    }

    public dbDemo(File dbProperties)throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream(dbProperties));
    }
}
