package A00980851.util.cli;

import A00980851.data.Inventory;
import A00980851.io.reports.InventoryReport;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Name            Japneet Johal A00 980 851
 * Project Name    AssignmentOne
 * Class  Name     InventoryOptions
* Date            2017-06-01
 */
public class InventoryOptions {
    /**
     * Main driver of this class checks what arugments are present and acts on the apporiate action
     * @param args
     * @param inventoryLinkedList
     * @throws ParseException
     */
    private static  final Logger LOG = LogManager.getLogger();

    public  void inventoryCliCheck(String[] args, LinkedList<Inventory> inventoryLinkedList)throws ParseException {
        SetupCLI.process(args);

        boolean description= false;
        boolean quantity = false;

        if (SetupCLI.hasInventory()){
            LOG.info("The inventory option has been chosen as a argument");
            InventoryReport inventoryReport = new InventoryReport();

            if (SetupCLI.hasDesc()){
                inventoryLinkedList.sort((s1, s2)-> s1.getDescription().compareTo(s2.getDescription())); // sorting by description
                description =true;
            }
            if (SetupCLI.hasCount()){
                inventoryLinkedList.sort((s1, s2)-> Integer.valueOf(s1.getQuantity()) - Integer.valueOf(s2.getQuantity())); // sorting by quantity
                quantity=true;
            }

            if (SetupCLI.hasTotal()){
                addTotal(inventoryLinkedList);
            }

            if (SetupCLI.isDescenindingAny()){
                if (description==true)
                    inventoryLinkedList.sort((s1, s2)-> s1.getDescription().compareTo(s2.getDescription())*-1);
                if (quantity==true)
                    inventoryLinkedList.sort((s1, s2)-> Integer.valueOf(s1.getQuantity()).compareTo(Integer.valueOf(s2.getQuantity()))*-1);

            }
            if (SetupCLI.hasmake()!=null){
                String type = SetupCLI.hasmake();
                filter(inventoryLinkedList, type);
            }
            inventoryReport.printCustomer(inventoryLinkedList);
        }
    }

    /**
     * private method that is spliting up make+model
     * @param inventoryLinkedList
     * @param type
     */
    private  void filter(LinkedList<Inventory> inventoryLinkedList,String type) {
        Iterator<Inventory> itr = inventoryLinkedList.iterator();

        while (itr.hasNext()){
            String entry = itr.next().getMotorCyleID();
            String make = findMake(entry);
            if (!make.equals(type)){
                itr.remove();
            }
        }
    }

    /**
     * Method to find split make+model up
     * @param entry
     * @return
     */
    private  String findMake(String entry) {
        int pointToConcat = entry.indexOf("+");
        String make = entry.substring(0,pointToConcat);
        return make;
    }

    /**
     * adds the total to the end of the inventory class
     * @param inventoryLinkedList
     */
    private  void addTotal(LinkedList<Inventory> inventoryLinkedList) {
        for (int i=0; i<inventoryLinkedList.size();i++){
            Inventory inventory = inventoryLinkedList.get(i);
            inventory.setTotal(String.valueOf(Float.valueOf(inventory.getPrice())*Integer.valueOf(inventory.getQuantity())));
        }
    }
}

