package A00980851.jdbc.dao;

import A00980851.jdbc.DBConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Properties;

/**
 * Name            Japneet Johal A00 980 851
 * Project Name    A00980851Lab7
 * Class  Name     DatabaseSetup
 * Date            2017-06-04
 * Setting up the DB
 */
public class DatabaseSetup {

    public static final String DB_DRIVER_KEY = "db.driver";
    public static final String DB_URL_KEY = "db.url";
    public static final String DB_USER_KEY = "db.user";
    public static final String DB_PASSWORD_KEY = "db.password";

    private static Connection connection;
    private final Properties properties;

    private static final Logger LOG = LogManager.getLogger();

    /**
     * general constructor
     * @param properties
     */
    public DatabaseSetup(Properties properties){
        this.properties = properties;
    }

    /**
     *
     * @return Connection
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException{
        if (connection != null){
            return connection;
        }
        try {
            connect();
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * connect to DB
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private void connect()throws SQLException,ClassNotFoundException {
        Class.forName(properties.getProperty(DBConstants.DB_DRIVER_KEY));

        LOG.info("Driver loading");

        connection = DriverManager.getConnection(properties.getProperty(DB_URL_KEY), properties.getProperty(DB_USER_KEY),
                properties.getProperty(DB_PASSWORD_KEY));

        LOG.info("LOG database connected database");
    }

    /**
     * shutdown
     * @throws SQLException
     */
    public void shutdown()throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }

    /**
     * checks if table exsits
     * @param tableName
     * @return
     * @throws SQLException
     */
    public static boolean tableExists(String tableName) throws SQLException {
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet resultSet = null;
        String rsTableName = null;

        try {
            resultSet = databaseMetaData.getTables(connection.getCatalog(), "%", "%", null);
            while (resultSet.next()) {
                rsTableName = resultSet.getString("TABLE_NAME");
                if (rsTableName.equalsIgnoreCase(tableName)) {
                    return true;
                }
            }
        } finally {
            resultSet.close();
        }

        return false;
    }

}
