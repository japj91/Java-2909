package A00980851.util.CLI;

import org.apache.commons.cli.ParseException;

/**
 * Name            Japneet Johal A00 980 851
 * Project Name    A00980851Lab7
 * Class  Name     customerCli
 * Date            2017-06-07
 */
public class customerCli {
    /**
     * checking if CLI exsits
     * @param args
     * @return
     * @throws ParseException
     */
    public boolean checkOptions(String[] args)throws ParseException{
        cliSetup.process(args);
        return cliSetup.hasDrop();
    }
}
