package apachecommonscli;

import org.apache.commons.cli.ParseException;

/**
 * Project: SimpleApacheCliExample
 * File: CliExample.java
 * Date: Oct 7, 2016
 * Time: 10:03:17 AM
 */

/**
 * Sample app to test Apache Commons CLI
 * Input: -u=ffish -password=carp -ao -banana
 * Result:
 * USERNAME = ffish
 * PASSWORD = carp
 * APPLE = true
 * BANANA = true
 * ORANGE = true
 * 
 * 
 * @see https://commons.apache.org/proper/commons-cli/
 * 
 * @author Sam Cirka, A00123456
 *
 */
public class CliExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CliOptions.process(args);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}

		System.out.format("%s = %s%n", CliOptions.Value.USERNAME, CliOptions.getUsername());
		System.out.format("%s = %s%n", CliOptions.Value.PASSWORD, CliOptions.getPassword());
		System.out.format("%s = %s%n", CliOptions.Value.APPLE, CliOptions.isAppleOptionSet());
		System.out.format("%s = %s%n", CliOptions.Value.BANANA, CliOptions.isBananaOptionSet());
		System.out.format("%s = %s%n", CliOptions.Value.ORANGE, CliOptions.isOrangeOptionSet());

	}

}
