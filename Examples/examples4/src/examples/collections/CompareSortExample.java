package examples.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CompareSortExample {

	public static void main(String[] args) {
		new CompareSortExample();
	}

	public CompareSortExample() {
		String[]test = {"ga","qq","Ga"};
		List<String> animals = Arrays.asList("Leopard", "Deer", "Cow", "Cat", "Cougar", "Lion", "Dog");
		System.out.println("- Natural sort --------------------------");
		Collections.sort(animals);
		CollectionUtil.display(animals);

		System.out.println("- CompareByLength --------------------------");

		// java 7 sort using an outer class
		StringSorters.CompareByLength byLength = new StringSorters.CompareByLength();
		animals.sort(byLength);
		CollectionUtil.display(animals);

		// java 7 sort using an anonymous inner class
		System.out.println("- anonymous inner class Comparator --------------------------");
		Collections.sort(animals, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				// our sorting criteria is length of string
				return s1.length() - s2.length();
			}
		});

		// java 8 sort using lambda expressions
		animals.sort((String s1, String s2) -> s1.length() - s2.length());
		// and again, but with lambdas, the parameter type is optional
		animals.sort((s1, s2) -> s1.length() - s2.length());

		System.out.println("- CompareByReverseLength --------------------------");
		animals.sort(new StringSorters.CompareByReverseLength());
		CollectionUtil.display(animals);

		// and here's a rather sill compare by last letter
		System.out.println("- CompareByLastLetter --------------------------");
		animals.sort(new StringSorters.CompareByLastLetter());
		CollectionUtil.display(animals);

		System.out.println("--------------------------");

		List<Part> parts = new ArrayList<>();
		parts.add(new Part("32728523463", "CLUTCH LEVER ASSEMBLY, ADJUS", 19337, 7));
		parts.add(new Part("6717700591", "COLLAR BUSH", 1385, 19));

		System.out.println("- Natural sort --------------------------");
		Collections.sort(parts);
		CollectionUtil.display(parts);

		System.out.println("- CompareItemByDescription --------------------------");
		parts.sort(new CompareItemByDescription());
		CollectionUtil.display(parts);
	}

}
