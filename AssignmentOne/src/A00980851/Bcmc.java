package A00980851;

import A00980851.data.Customer;
import A00980851.data.Inventory;
import A00980851.data.Motorcycle;
import A00980851.io.readers.CustomerReader;
import A00980851.io.readers.InventoryReader;
import A00980851.io.readers.MotorCycleReader;
import A00980851.util.cli.InventoryOptions;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import A00980851.util.cli.CustomersOptions;
import A00980851.util.cli.MotorCycleOptions;
import A00980851.util.ApplicationException;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;


/**
 * Name            Japneet Johal A00 980 851
 * Project Name    AssignmentOne
 * Class  Name     A00980851.Bcmc
 * Date            2017-05-28
 *
 * Assignment 1 - it is the driver of the program
 */
public class Bcmc {

    private static final String Log4J_CONFIG_FILE = "log4j2.xml";

    static {
        configureLogging();
    }

    private static  final Logger LOG = LogManager.getLogger();

    LinkedList<Customer> customerLinkedList;
    LinkedList<Inventory> inventoryLinkedList;
    Motorcycle[] motorCyclesArray;


    /**
     * Main class of the program
     * @param args
     */
    public static void main(String[] args) {

        LOG.info("");   // Blank Log.info is put here on purpose it makes it easier to read the log.
        Instant startTime = Instant.now();

        LOG.info("Start Time "+startTime);

        new Bcmc().AssignmentOne(args);

        Instant finishTime = Instant.now();
        LOG.info("Finish Time "+finishTime);

        LOG.info(String.format("Time application took to run %d ms ",Duration.between(startTime,finishTime).toMillis()));
    }


    /**
     * This method takes the args and then gets linked lists and runs the application
     * @param args
     */
    public void AssignmentOne(String[] args){
        try {
            customerLinkedList = new CustomerReader().parse(); // passing log into these objects so they can log info statements
            inventoryLinkedList = new InventoryReader().parse();
            motorCyclesArray = new MotorCycleReader().parse();

            CustomersOptions checkOptions = new CustomersOptions();
            InventoryOptions inventoryOptions = new InventoryOptions();
            MotorCycleOptions motorCycleOptions = new MotorCycleOptions();

            checkOptions.customersCliCheck(args,customerLinkedList);
            inventoryOptions.inventoryCliCheck(args,inventoryLinkedList);
            motorCycleOptions.checkOptions(args,motorCyclesArray);

        }catch (ApplicationException a){
            LOG.error(a.getMessage());
        }
        catch (ParseException ex){
            LOG.error(ex.getMessage());
        }
    }

    /**
     * this method configures the logging
     */
    private static void configureLogging() {
        ConfigurationSource source;
        try {
            source = new ConfigurationSource(new FileInputStream(Log4J_CONFIG_FILE));
            Configurator.initialize(null,source);
        } catch (IOException e) {
            System.out.println("Cant find the log 4j File");
            e.printStackTrace();
        }
    }

}
