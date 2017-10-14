/**
 * 
 */
package examples.collections;

import java.util.Comparator;

/**
 * @author Sam Cirka, A00123456
 * 
 */
public class CompareItemByDescription implements Comparator<Part> {
	public int compare(Part item1, Part item2) {
		return item1.getDescription().compareTo(item2.getDescription());
	}
}
