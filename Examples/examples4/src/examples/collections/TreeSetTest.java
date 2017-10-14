package examples.collections;

/**
 * @version 1.00 1999-07-07
 * @author Cay Horstmann
 */

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This program sorts a set of item by comparing their descriptions.
 */
public class TreeSetTest {
	public static void main(String[] args) {
		SortedSet<Part> parts = new TreeSet<>();
		parts.add(new Part("1234", "Toaster"));
		parts.add(new Part("4562", "Widget"));
		parts.add(new Part("9912", "Modem"));
		System.out.println(parts);

		SortedSet<Part> sortByDescription = new TreeSet<>(new Comparator<Part>() {
			@Override
			public int compare(Part itemA, Part itemB) {
				String descrA = itemA.getDescription();
				String descrB = itemB.getDescription();
				return descrA.compareTo(descrB);
			}
		});

		sortByDescription.addAll(parts);
		System.out.println(sortByDescription);
	}
}
