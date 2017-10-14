package Test;

import java.sql.*;

/**
 * Name            Japneet Johal A00 980 851
 * Project Name    examples7
 * Class  Name     dbutil
 * Date            2017-06-03
 */

public class dbutil {

    public static boolean tableExists (Connection connection, String tableName) throws SQLException{
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet resultSet = null;
        String rsTableName = null;

        try{
            resultSet = databaseMetaData.getTables(connection.getCatalog(),"%","%",null);
            while (resultSet.next()){
                rsTableName = resultSet.getString("TABLE_NAME");
                if (rsTableName.equalsIgnoreCase(tableName)){
                    return true;
                }

            }
        }finally {
            resultSet.close();
        }
        return false;
    }
    public static int executeUpdate (Statement statement, String sql) throws SQLException{
        System.out.println("Ready to execute" + sql);
        int count = statement.executeUpdate(sql);

        return count;
    }

    public static int execute(Statement statement, String sql) throws SQLException{
        int count = statement.executeUpdate(sql);
        return count;
    }

    public static ResultSet executeQuery(Statement statement, String sql) throws SQLException{
        System.out.println("Executing query "+ sql);
        return statement.executeQuery(sql);
    }

}
