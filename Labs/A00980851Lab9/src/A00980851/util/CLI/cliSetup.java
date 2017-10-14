package A00980851.util.CLI;

import org.apache.commons.cli.*;
import java.text.ParseException;

/**
 * Name            Japneet Johal A00 980 851
 * Project Name    A00980851Lab7
 * Class  Name     cliSetup
 * Date            2017-06-07
 */
public class cliSetup {
    private static CommandLine cmd;

    /**
     * sets up the CLI command line
     * @param args
     * @throws ParseException
     */
    public static void process(String[] args)throws org.apache.commons.cli.ParseException{
        Options options = createOptions();
        CommandLineParser cli = new DefaultParser();

        cmd = cli.parse(options,args);
    }

    /**
     *
     * @return Options
     */
    private static Options createOptions() {
        Options options = new Options();
        options.addOption(Option.builder(Value.drop.option).longOpt(Value.drop.longoption).desc("drop table").build());

        return options;
    }

    /**
     * Creating enums
     */
    public enum Value{
        drop("d","drop");

        private final String option;
        private final String longoption;

        Value(String option,String longoption){
            this.option = option;
            this.longoption = longoption;
        }
    }

    public static boolean hasDrop(){
        return cmd.hasOption(Value.drop.option);
    }
}
