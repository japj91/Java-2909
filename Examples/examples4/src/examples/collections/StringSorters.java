/**
 * Project: examples4
 * File: StringSorters.java
 * Date: Oct 3, 2016
 * Time: 3:05:52 PM
 */

package examples.collections;

import java.util.Comparator;

/**
 * @author Sam Cirka, A00123456
 *
 */
public class StringSorters {

	public static class CompareByLength implements Comparator<String> {

		/**
		 * The sorting criteria is length of string from longest to shortest
		 */
		@Override
		public int compare(String string1, String string2) {
			return string1.length() - string2.length();
		}
	}

	public static class CompareByReverseLength implements Comparator<String> {

		/**
		 * The sorting criteria is length of string from shortest to longest
		 */
		@Override
		public int compare(String string1, String string2) {
			return string2.length() - string1.length();
		}
	}

	public static class CompareByLastLetter implements Comparator<String> {

		/**
		 * The sorting compares the last letter of the string. A bit silly really.
		 * This comparison isn't very robust as it doesn't check for nulls or empty strings.
		 * It also doesn't check for case sensitivity, making uppercase 'A' smaller than lowercase 'a'
		 */
		@Override
		public int compare(String string1, String string2) {
			return string1.charAt(string1.length() - 1) - string2.charAt(string2.length() - 1);
		}

	}

}
