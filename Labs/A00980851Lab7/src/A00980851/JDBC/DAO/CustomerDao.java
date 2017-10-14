package A00980851.JDBC.DAO;

import A00980851.JDBC.DBConstants;
import A00980851.data.Customer;
import A00980851.io.ApplicationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

/**
 * database setup is the connection to derby database. Customer dao is the class that implements the actions
 * dbTest directs what the customer database needs to be doing. for exmaple add, delete, update. Customer dao extends DAO because it inherits
 * add delete update fucntiosn
 * Name            Japneet Johal A00 980 851
 * Project Name    A00980851Lab7
 * Class  Name     CustomerDao
 * Date            2017-06-05
 *
 * Customer Database class
 */
public class CustomerDao extends DAO {

    static final String TABLE_NAME = DBConstants.DB_TABLE_NAME;
    static Connection connection;
    private static final Logger LOG = LogManager.getLogger();

    /**
     *
     * @param database
     */
    protected CustomerDao(DatabaseSetup database) {
        super(database, TABLE_NAME);
    }

    /**
     * Method checks if customers are already in table if they are it deletes them
     * @param sql
     * @throws SQLException
     */
    public void drop(String sql) throws SQLException{
        String tableName = String.format(" drop table %s", sql);
        connection = database.getConnection();
        Statement statement = connection.createStatement();

        if (DatabaseSetup.tableExists(sql)==true){
            statement.executeUpdate(tableName);
        }
        LOG.info("Customers are present in table- Deleting table");

    }

    /**
     *
     * @param customerLinkedList
     * @throws SQLException
     */
    public void add(LinkedList<Customer> customerLinkedList) throws SQLException {

        Statement statement = null;
        try {
             connection = database.getConnection();
             statement = connection.createStatement();
            for (Customer customer : customerLinkedList) {
                String sql = String.format("insert into jap values ('%s', '%s','%s', '%s', '%s', '%s', '%s', '%s', '%s')", customer.getIdentifier(), customer.getFirstName(), customer.getLastName(), customer.getStreetName(), customer.getCity(), customer.getPostalCode(), customer.getPhoneNumber()
                        , customer.getEmailAddress(), toTimestamp(customer.getDate()));

                statement.executeUpdate(sql);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            statement.close();
        }
        LOG.info("data inserted successfully");

    }

    /**
     *
     * @param id
     * @throws SQLException
     */

    public void delete(String id) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = String.format("Delete from jap where identifier = '%s' ",id);
        statement.executeUpdate(sql);
        LOG.info("Data deleted");
    }

    /**
     *
     * @param customer
     * @throws SQLException
     * Updating the customer
     */
    public void update(Customer customer) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = String.format("UPDATE %s set %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s' WHERE %s='%s'",
                DBConstants.DB_TABLE_NAME,
                DBConstants.IDENTIFIER, customer.getIdentifier(),
                DBConstants.FirstName, customer.getFirstName(),
                DBConstants.lastName, customer.getLastName(),
                DBConstants.streetName, customer.getStreetName(),
                DBConstants.city, customer.getCity(),
                DBConstants.postalCode, customer.getPostalCode(),
                DBConstants.phoneNumber, customer.getPhoneNumber(),
                DBConstants.emailAddress, customer.getEmailAddress(),
                DBConstants.date, toTimestamp(customer.getDate()),
                DBConstants.IDENTIFIER, customer.getIdentifier());
        statement.executeUpdate(sql);

    }

    /**
     *
     * @param date
     * @return
     */
    private Timestamp toTimestamp(LocalDate date) {
        return Timestamp.valueOf(LocalDateTime.of(date, LocalTime.now()));
    }

    /**
     * TEST METHOD PRINTS ALL RECORDS IN JAP TABLE
     * @throws SQLException
     */
    private void query()throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select * from jap");
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        int rows = resultSetMetaData.getColumnCount();

        while (resultSet.next()){
            for (int i = 1; i<=rows;i++){

                String data = resultSet.getString(i);
                System.out.printf(data+" ");
            }
            System.out.println();
        }
    }

    /**
     * get Ids
     * @return
     * @throws SQLException
     */
    public LinkedList<String> getIDs()throws SQLException{
        LinkedList<String> idLinkedList = new LinkedList<>();
        Statement statement = connection.createStatement();

        String sql = String.format("Select identifier from %s",DBConstants.DB_TABLE_NAME);

        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int rowCount = resultSetMetaData.getColumnCount();
        while (resultSet.next()){
            for (int i =1; i<=rowCount;i++){
                idLinkedList.add(resultSet.getString(i));
            }
        }
        return idLinkedList;
    }

    /**
     *
     * @param customerIds
     * @return
     * @throws SQLException
     * @throws ApplicationException
     */

    public LinkedList<Customer> findCustomers(LinkedList<String> customerIds)throws SQLException,ApplicationException {

        LinkedList<Customer> customerLinkedList = new LinkedList<>();

        Statement statement = connection.createStatement();

        for (String Id:customerIds) {
            String sql = String.format("Select * from %s where identifier = '%s' ", DBConstants.DB_TABLE_NAME,Id);

            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            int rows = resultSetMetaData.getColumnCount();
            System.out.println(sql);
            customerLinkedList.add(getCustomer(resultSet,rows));
        }
        return customerLinkedList;
    }


    public Customer findIndivudalCustomer(int ID) throws SQLException,ApplicationException{
        Statement statement = connection.createStatement();

        String sql = String.format("Select * from %s where identifier = '%d' ",DBConstants.DB_TABLE_NAME,ID);

        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        int rows = resultSetMetaData.getColumnCount();
        return getCustomer(resultSet,rows);
    }

    /**
     * seems like the for is inside of individual row in the db thats why u go until rows
     * @param resultSet
     * @param rows
     * @return
     * @throws SQLException
     * @throws ApplicationException
     */
    private Customer getCustomer(ResultSet resultSet, int rows)throws SQLException, ApplicationException {
        while (resultSet.next()){
            String[] list = new String[9];

            for (int i = 1; i<=rows; i++){
                String data = resultSet.getString(i);
                list[i-1] = data;

            }
            Customer customer = new Customer(Integer.valueOf(list[0]),list[1],list[2],list[3],list[4],list[5],list[6],list[7],getCalendar(list[8]));
            return customer;
        }
        return null;
    }

    /**
     * Conversion Tool
     * @param entry
     * @return
     * @throws ApplicationException
     */
    private LocalDate getCalendar(String entry) throws ApplicationException {
        int year = Integer.valueOf(entry.substring(0, 4));
        int month = Integer.valueOf(entry.substring(5, 7));
        int day = Integer.valueOf(entry.substring(8, 10));


        if (month > 12) {

            LOG.error("Months are greater then 12");

            throw new ApplicationException("The date " + month + " for months is invalid");
        }
        else if (day > 31) {

            LOG.error("More then 31 days entered for the month");
            throw new ApplicationException("The date " + day + " for data is invalid");
        }

        LocalDate fromIsoDate = LocalDate.of(year, month, day);

        return fromIsoDate;
    }

    @Override
    public void create() throws SQLException {

    }
}
