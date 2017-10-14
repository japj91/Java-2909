package A00980851.JDBC.DAO;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Name            Japneet Johal A00 980 851
 * Project Name    A00980851Lab7
 * Class  Name     DAO
 * Date            2017-06-04
 * Abstract Class
 */
public abstract class DAO {

    protected final DatabaseSetup database;
    protected final String tableName;

    private static final Logger LOG = LogManager.getLogger();

    /**
     *
     * @param database
     * @param tableName
     */
    protected DAO(DatabaseSetup database, String tableName){
        this.database = database;
        this.tableName = tableName;
    }

    /**
     *
     * @throws SQLException
     */
    public abstract void create() throws SQLException;

    /**
     *
     * @param sql
     * @throws SQLException
     */
    protected void create(String sql) throws SQLException{
        Statement statement = null;
        try {
            Connection connection = database.getConnection();
            statement = connection.createStatement();
            LOG.info("Table created sucessfully");
            statement.execute(sql);
        }finally {
            close(statement);
        }
    }

    /**
     *
     * @param statement
     */
    protected void close(Statement statement){
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }



}
