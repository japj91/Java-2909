package examples.logging.log4j;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

public class BasicLogging {

	private static final String LOG4J_CONFIG_FILENAME = "log4j2.xml";
	private static Logger LOG;
	
	static {
		configureLogging();
		
		LOG = LogManager.getLogger(BasicLogging.class);
	}

	public static void main(String[] args) throws IOException {
		LOG.info("Starting BasicLogging.");
		
		BasicLogging basicLogging = new BasicLogging();

		try {
			basicLogging.foo();
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		
		AnotherClass.bar();
		
		LOG.info("Exiting BasicLogging.");
	}
	
	private BasicLogging() {
		LOG.debug("BasicLogging constructor.");
	}

	private void foo() throws Exception {
		LOG.debug("foo() called.");
		
		if (true) {
			throw new Exception("Ouch!");
		}
	}

	private static void configureLogging() {
		ConfigurationSource source;
		try {
			source = new ConfigurationSource(new FileInputStream(LOG4J_CONFIG_FILENAME));
			Configurator.initialize(null, source);
			
		} catch (IOException e) {
			System.out.println(String.format("Can't find the log4j logging configuration file %s.", LOG4J_CONFIG_FILENAME));
		}
	}

}
