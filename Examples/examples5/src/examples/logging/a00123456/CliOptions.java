package examples.logging.a00123456;

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
		Options options = new Options();

		options.addOption(
				Option.builder(Value.ARG.option).longOpt(Value.ARG.longOption).desc("test incorrect number of arguments in a Customer").build());
		options.addOption(Option.builder(Value.EMAIL.option).longOpt(Value.EMAIL.longOption).desc("test a bad email address").build());
		options.addOption(Option.builder(Value.DATE.option).longOpt(Value.DATE.longOption).desc("test an invalid date").build());

		return options;
	}

	public static boolean isTestArgOptionSet() {
		return cmd.hasOption(Value.ARG.option);
	}

	public static boolean isTestEmailOptionSet() {
		return cmd.hasOption(Value.EMAIL.option);
	}

	public static boolean isTestDateOptionSet() {
		return cmd.hasOption(Value.DATE.option);
	}

	public enum Value {
		ARG("a", "testarg"), //
		EMAIL("e", "testemail"), //
		DATE("d", "testdate");

		private final String option;
		private final String longOption;

		Value(String option, String longOption) {
			this.option = option;
			this.longOption = longOption;
		}
	}

}
