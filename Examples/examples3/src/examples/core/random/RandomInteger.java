package examples.core.random;

import java.util.Random;

/** Generate 10 random integers in the range 0..99. */
public final class RandomInteger {

	public static final void main(String... aArgs) {
		log("Generating 10 random integers in range 0..99.");

		// note a single Random object is reused here
		Random randomGenerator = new Random();
		for (int i = 1; i <= 10; i++) {
			int randomInt = randomGenerator.nextInt(100);
			log("Generated : " + randomInt);
		}

		log("Done.");
	}

	private static void log(String aMessage) {
		System.out.println(aMessage);
	}
}
