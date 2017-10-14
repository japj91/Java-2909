package A00980851.util.cli;

import org.apache.commons.cli.*;

/**
 * Name            Japneet Johal A00 980 851
 * Project Name    AssignmentOne
 * Class  Name     opt
 * Date            2017-05-30
 */
public class SetupCLI {

    private static CommandLine cmd;

    /**
     * sets up the CLI command line
     * @param args
     * @throws ParseException
     */
    public static void process(String[] args) throws ParseException {
        Options options = createOptions();
        CommandLineParser cli = new DefaultParser();

        cmd = cli.parse(options,args);
    }

    /**
     *
     * @return
     */
    private static Options createOptions() {

        Options options = new Options();
        options.addOption(Option.builder(Value.service.option).longOpt(Value.service.longoption).desc("service").build());
        options.addOption(Option.builder(Value.inventory.option).longOpt(Value.inventory.longoption).desc("inventory").build());
        options.addOption(Option.builder(Value.customers.option).longOpt(Value.customers.longoption).desc("customers").build());
        options.addOption(Option.builder(Value.total.option).longOpt(Value.total.longoption).desc("total").build());
        options.addOption(Option.builder(Value.byDesc.option).longOpt(Value.byDesc.longoption).desc("byDesc").build());
        options.addOption(Option.builder(Value.byCount.option).longOpt(Value.byCount.longoption).desc("byCount").build());
        options.addOption(Option.builder(Value.byJoinDate.option).longOpt(Value.byJoinDate.longoption).desc("byJoinDate").build());
        options.addOption(Option.builder(Value.make.option).longOpt(Value.make.longoption).desc("make").hasArg().build());
        options.addOption(Option.builder(Value.desc.option).longOpt(Value.desc.longoption).desc("desc").build());

        return options;
    }

    /**
     *
     * @return boolean
     */
    public static boolean hasService(){
        return (cmd.hasOption(Value.service.option));
    }
    /**
     *
     * @return boolean
     */
    public static boolean hasInventory(){
        return (cmd.hasOption(Value.inventory.option));
    }
    /**
     *
     * @return boolean
     */
    public static boolean hasCustomers(){
        return (cmd.hasOption(Value.customers.option));
    }
    /**
     *
     * @return boolean
     */
    public static boolean hasTotal(){
        return (cmd.hasOption(Value.total.option));
    }
    /**
     *
     * @return boolean
     */
    public static boolean hasDesc(){
        return (cmd.hasOption(Value.byDesc.option));
    }
    /**
     *
     * @return boolean
     */
    public static boolean hasCount(){
        return (cmd.hasOption(Value.byCount.option));
    }
    /**
     *
     * @return boolean
     */
    public static boolean hasJoinDate(){
        return (cmd.hasOption(Value.byJoinDate.option));
    }
    /**
     *
     * @return String
     */
    public static String hasmake(){
        return (cmd.getOptionValue(Value.make.option));
    }
    /**
     *
     * @return boolean
     */
    public static boolean isDescenindingAny(){
        return (cmd.hasOption(Value.desc.option));
    }

    /**
     * Enum for values both long and short option
     */
    public enum Value{
        service("s","service"),
        inventory("i","inventory"),
        customers("c","customers"),
        total("t","Total"),
        byDesc("D","by_description"), // sorts parts by name ascending
        byCount("C","by_count"),
        byJoinDate("J","by_join_date"),
        make("m","make"),
        desc("d","desc");

        private final String option;
        private final String longoption;

        Value(String option, String longoption){
            this.option=option;
            this.longoption = longoption;
        }
    }



}
