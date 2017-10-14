package A00980851.io.readers;

import A00980851.data.Motorcycle;
import A00980851.util.ApplicationException;
import A00980851.util.StringPrinting;
import A00980851.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Name            Japneet Johal A00 980 851
 * Project Name    AssignmentOne
 * Class  Name     MotorCycleReader
 * Date            2017-05-28
 * Motorcycle.dat reading class
 */
public class MotorCycleReader extends StringPrinting{
    private final String FILENAME = "motorcycles.dat";
    private final String PARSETOKEN = "\\|";
    Validator validator;
    private static  final Logger LOG = LogManager.getLogger();
    /**
     * driver of this motorcycle class
     * @return
     * @throws ApplicationException
     */
    public Motorcycle[] parse() throws ApplicationException{
        LOG.info("Reading motorcycle objects");
        validator = new Validator();

        Scanner scanner = openFile();
        LinkedList<String> customerStrings = readFile(scanner);
        LOG.info("Finished reading motorcycle objects");

        return praseStrings(customerStrings);
    }

    /**
     * opening file motorcycle.dat file
     * @return
     * @throws ApplicationException
     */
    private Scanner openFile() throws ApplicationException {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(FILENAME));
        }catch (FileNotFoundException ex){
            throw new ApplicationException(String.format("File %s not found MotorCycleReader Class",FILENAME));
        }
        return scanner;
    }

    /**
     * reading the entires
     * @param scanner
     * @return linkedlist
     */
    private LinkedList<String> readFile(Scanner scanner) {

       // scanner.nextLine();
        LinkedList<String> customers = new LinkedList<>();
        scanner.nextLine();
        while (scanner.hasNextLine()){
            String entry = scanner.nextLine();
            customers.add(entry);
        }
        LOG.debug(String.format("read in %s customer objects ",customers.size()));
        return customers;
    }

    /**
     *
     * @param customerStrings
     * @return linkedlist of motorcycles
     * @throws ApplicationException
     */
    private Motorcycle[] praseStrings(LinkedList<String> customerStrings)throws ApplicationException {

        LinkedList<Motorcycle> motorCycle = new LinkedList<>();
        Motorcycle[] motorcycles = new Motorcycle[8];

        for (int i =0; i<customerStrings.size();i++){

            String[] motorCycleReader = customerStrings.get(i).split(PARSETOKEN);
            motorCycle.add(makeInventory(motorCycleReader));
            motorcycles[i] = makeInventory(motorCycleReader);
        }
        return motorcycles;
    }

    /**
     * checks lenght of list and populates motorcycle objects
     * @param motorCycle
     * @return
     * @throws ApplicationException
     */
    private Motorcycle makeInventory(String[] motorCycle)throws ApplicationException {

        if (motorCycle.length != 7){
            throw new ApplicationException(String.format("Arguments length not correct %s - MotorCycleReader Class System Exited",getString(motorCycle)));
        }

        String id = (motorCycle[0]);

        String make = motorCycle[1];

        String model = motorCycle[2];

        String year = motorCycle[3];

        String serialNumber = motorCycle[4];

        String mileage = motorCycle[5];

        Motorcycle motorcycle = new Motorcycle(id,make,model,year,serialNumber,mileage);

        return motorcycle ;
    }


}
