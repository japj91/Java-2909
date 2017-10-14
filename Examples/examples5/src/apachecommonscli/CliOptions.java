package apachecommonscli;

/**
 * Project: SimpleApacheCliExample
 * File: CliOptions.java
 * Date: Oct 7, 2016
 * Time: 10:09:03 AM
 */

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Demonstrates the use of Apache Commons CLI
 * 
 * @see https://commons.apache.org/proper/commons-cli/
 * @author Sam Cirka, A00123456
 *
 */
public class CliOptions {

	private static CommandLine cmd;

	public static void process(String[] args) throws ParseException {
		Options options = createOptions();
		CommandLineParser cli = new DefaultParser();

		cmd = cli.parse(options, args);
	}

	private static Options createOptions() {
		// create Options object
		Options options = new Options();

		// add username option; requires and argument to specified after the option
		options.addOption(Option.builder(Value.USERNAME.option).longOpt(Value.USERNAME.longOption).hasArg().desc("username").build());

		// add password option; requires and argument to specified after the option
		options.addOption(Option.builder(Value.PASSWORD.option).longOpt(Value.PASSWORD.longOption).hasArg().desc("password").build());

		// add orange option
		options.addOption(Option.builder(Value.ORANGE.option).longOpt(Value.ORANGE.longOption).desc("process all the oranges").build());

		// add orange option
		options.addOption(Option.builder(Value.BANANA.option).longOpt(Value.BANANA.longOption).desc("process all the bananas").build());

		// add orange option
		options.addOption(Option.builder(Value.APPLE.option).longOpt(Value.APPLE.longOption).desc("process all the apples").build());

		return options;
	}

	/**
	 * @return the username
	 */
	public static String getUsername() {
		return cmd.getOptionValue(Value.USERNAME.option);
	}

	/**
	 * @return the password
	 */
	public static String getPassword() {
		return cmd.getOptionValue(Value.PASSWORD.option);
	}

	/**
	 * @return the orangeOptionSet
	 */
	public static boolean isOrangeOptionSet() {
		return cmd.hasOption(Value.ORANGE.option);
	}

	/**
	 * @return the bananaOptionSet
	 */
	public static boolean isBananaOptionSet() {
		return cmd.hasOption(Value.BANANA.option);
	}

	/**
	 * @return the appleOptionSet
	 */
	public static boolean isAppleOptionSet() {
		return cmd.hasOption(Value.APPLE.longOption);
	}

	public enum Value {
		USERNAME("u", "username"), //
		PASSWORD("p", "password"), //
		ORANGE("o", "orange"), //
		BANANA("b", "banana"), //
		APPLE("a", "apple");

		private final String option;
		private final String longOption;

		Value(String option, String longOption) {
			this.option = option;
			this.longOption = longOption;
		}
	}

}
