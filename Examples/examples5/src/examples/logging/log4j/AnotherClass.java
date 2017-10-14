/**
 * Project: examples5
 * File: AnotherClass.java
 * Date: Feb 9, 2015
 * Time: 9:21:43 PM
 */

package examples.logging.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Sam Cirka, A00123456
 *
 */
public class AnotherClass {

	private static final Logger LOG = LogManager.getLogger();

	public AnotherClass() {
		LOG.debug("AnotherClass constructor.");
	}

	static void bar() {
		LOG.info("Entered bar.");
	}
}
