package examples.core.random;

public class RandomExample {

	public static void main(String[] args) {

		// Math has a random method that returns a pseudo-random number between 0.0 and 1.0;
		System.out.println("Here's one random number: " + Math.random());
		System.out.println("Here's another random number: " + Math.random());
		System.out.println("And 10 random numbers betwee 1 and 25: ");
		for (int i = 0; i < 10; i++) {
			System.out.println((i + 1) + ". " + ((int) (Math.random() * 25)));
		}

	}

}
