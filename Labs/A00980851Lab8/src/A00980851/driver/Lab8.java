package A00980851.driver;

//Tortiose vs Hare

import io.NumGeneration;

public class Lab8 implements Runnable {

	private  Thread tortoise, hare;

	private NumGeneration numberGenerator = new NumGeneration();

	/**
	 * Starts each individual thread
	 */
	public Lab8()  {

		tortoise = new Thread(this);
		hare = new Thread(this);

		hare.setName("hare");
		tortoise.setName("tortoise");

		tortoise.start();
		hare.start();

	}

	/**
	 * takes the running total and displays it
	 * @param runningTotal
	 */
	public void report(int runningTotal ) {

		String threadName = Thread.currentThread().getName();

		System.out.printf("%s has %s ",threadName, runningTotal);


		if (runningTotal >= 100){

			System.out.println("\n");
			System.out.println(threadName+" WINS");
			System.exit(0);
			Runtime.getRuntime().halt(0);
		}

	}

	/**
	 * Runs each individual thread
	 */
	public void run() {
	int total = 0;

	while (total <= 100){

		int randomNumber = numberGenerator.getNumber();
		total += randomNumber;
		report(total);

		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println();
		}

	}

	/**
	 * main method of program starts all other programs
	 * @param args
	 */
	public static void main(String args[]) {
		new Lab8();
	}
}
