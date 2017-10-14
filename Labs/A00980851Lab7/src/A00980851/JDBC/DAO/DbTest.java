package A00980851.JDBC.DAO;

import A00980851.JDBC.DBConstants;
import A00980851.data.Customer;
import A00980851.io.ApplicationException;
import A00980851.io.CustomerReport;
import A00980851.util.CLI.customerCli;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Random;


/**
 * Name            Japneet Johal A00 980 851
 * Project Name    A00980851Lab7
 * Class  Name     DbTest
 * Date            2017-06-04
 *
 * Data base test class
 */

public class DbTest {

    private static DatabaseSetup database;
    private Properties dbProperties;

    private Connection connection;
    public LinkedList<Customer> customerLinkedList;

    CustomerDao customerDao;
    LinkedList<String> customersId;

    private static final Logger LOG = LogManager.getLogger();

    /**
     * Constructor defualt
     * @param linkedList
     */
    public DbTest(LinkedList<Customer> linkedList){
        this.customerLinkedList = linkedList;
    }

    /**
     * driver of this application
     * @param list
     * @throws ApplicationException
     * @throws IOException
     * @throws SQLException
     * @throws ParseException
     */
    public void load(String[] list) throws ApplicationException, IOException,SQLException,ParseException{

        File DbPropFile = new File(DBConstants.DB_PROPERTIES_FILENAME);

        if (!DbPropFile.exists()){
            throw new ApplicationException("Could not find Prop.properties file");
        }
        setUpDB(DbPropFile,customerLinkedList);
        run(list);

    }



    /**
     * String args is present b/c u are parsing command line arguemnts from the user
     * Preforms different DB options
     * @param args
     * @throws SQLException
     * @throws ParseException
     */
    private void run(String[] args)throws SQLException, ParseException{
        connect();

        customerCli cliSetup = new customerCli();

        if (cliSetup.checkOptions(args)){
            dropTables("jap");
            System.exit(0);
        }

        try {
            dropTables("jap");

            createTables();
            insertCustomers();

        }catch (Exception e){
            LOG.error(e.getCause());
            e.printStackTrace();
        }

    }

    public Customer randomCustomer() throws SQLException,ApplicationException{
        Random random = new Random();
        int randNum = random.nextInt(customerLinkedList.size())+1;
        Customer customer = customerDao.findIndivudalCustomer(randNum);
        return customer;
    }


    /**
     * delete a customer never used but implemented
     * @param id
     * @throws SQLException
     */
    private void delete(String id)throws SQLException{
        customerDao.delete(id);
    }

    /**
     * get customer IDS
     * @return
     * @throws SQLException
     */
    private LinkedList<String> getIds() throws SQLException{
        customersId = customerDao.getIDs();
        System.out.printf("Found %d Customers IDs %s\n",customersId.size(),customersId);
        return customersId;
    }


    /**
     * get Customers with CustomerIDs
     * @throws SQLException
     * @throws ApplicationException
     * @throws IOException
     */
    private void getCustomer ()throws SQLException,ApplicationException, IOException{
        LinkedList<Customer> customerLinkedList = customerDao.findCustomers(customersId);
        CustomerReport customerReader = new CustomerReport();

        customerReader.printToConsole(customerLinkedList);
    }

    /**
     * Implemented but not used updates a customers
     * @throws SQLException
     */
    private void update() throws SQLException{
        Customer.Builder customer = new Customer.Builder(1,"7783841700");
        customer.firstName("Jason");
        customer.lastName("Jason");
        customer.streetName("4915 29th Ave");
        customer.city("Vancouver");
        customer.postalCode("V81 L02");
        customer.emailAddress("jao@hotmail.com");
        customer.date(LocalDate.now());
        Customer customer1 = customer.build();
        customerDao.update(customer1);
    }

    /**
     * Add customers
     * @throws SQLException
     */
    private void insertCustomers()throws SQLException {
        customerDao.add(customerLinkedList);
    }

    /**
     * drop the tables
     * @param table
     * @throws SQLException
     */
    private void dropTables(String table) throws SQLException{
        customerDao = new CustomerDao(database);
        customerDao.drop(table);
    }

    /**
     * Create the tables
     * @throws SQLException
     */
    private void createTables() throws SQLException{
        String sql = "create table jap( identifier varchar(20), firstName varchar(20), lastName varChar(20), streetName varchar (50), city varchar(20), " +
                "postalCode varchar(15), phoneNumber varchar(15), emailAddress varchar(50), date varchar(25))";
        customerDao.create(sql);
    }

    /**
     * create connection to DB
     * @throws SQLException
     */
    private void connect() throws SQLException{
        this.connection = database.getConnection();
    }

    /**
     * setting of properites and LinkedList
     * @param dbPropFile
     * @param customers
     * @throws IOException
     */
    public void setUpDB(File dbPropFile, LinkedList<Customer> customers)throws IOException {
        this.customerLinkedList = customers;
        dbProperties = new Properties();
        dbProperties.load(new FileReader(dbPropFile));
        database = new DatabaseSetup(dbProperties);
    }
    public void shutdown() throws SQLException{
        database.shutdown();
    }
}
