package A00980851.util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Name            Japneet Johal A00 980 851
 * Project Name    A00980851Lab5
 * Class  Name     Logging
 * Date            2017-05-23
 */
public class Logging {

    public static final String LOG4J_CONFIG_FILENAME = "log4j2.xml";

    static {
        configureLogging();
    }

    private static final Logger LOG = LogManager.getLogger(Logging.class);

    public void info(String message){
        LOG.info(message);
    }

    public static void error(String message){
        LOG.error(message);
    }

    private static void configureLogging() {
        ConfigurationSource source;
        try {
            source = new ConfigurationSource(new FileInputStream(LOG4J_CONFIG_FILENAME));
            Configurator.initialize(null, source);
        } catch (IOException e) {
            System.out.println(String.format(
                    "Can't find the log4j logging configuration file %s.", LOG4J_CONFIG_FILENAME));
        }
    }


}
