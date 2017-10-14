/**
 * Project: examples10
 * File: DemoListModel.java
 * Date: Nov 28, 2016
 * Time: 11:50:01 AM
 */

package examples.mvc.listmodeldemo2.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

/**
 * @author Sam Cirka, A00123456
 *
 */
@SuppressWarnings("serial")
public abstract class DemoListModel extends AbstractListModel<String> {

	private List<String> strings = new ArrayList<>();

	/*
	 * (non-Javadoc)
	 * @see javax.swing.ListModel#getSize()
	 */
	@Override
	public int getSize() {
		return strings.size();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	@Override
	public String getElementAt(int index) {
		return strings.get(index);
	}

	/**
	 * Add an element to the list. Modify the behaviour of DefaultListModel.addElement to change the text to 'pig-latin'
	 * 
	 * @param element
	 *            element to be added
	 */
	public void add(String element) {
		add(-1, element);
	}

	/**
	 * Inserts the specified element at the specified position in this list
	 * (optional operation). Shifts the element currently at that position
	 * (if any) and any subsequent elements to the right (adds one to their
	 * indices).
	 *
	 * @param index
	 *            index at which the specified element is to be inserted
	 * @param element
	 *            element to be inserted
	 */
	public void add(int index, String element) {
		if (index == -1) {
			strings.add(element);
			index = strings.size() - 1;
		} else {
			strings.add(index, element);
		}

		fireIntervalAdded(this, index, index);
	}

	/**
	 * Removes the element at the specified position in this list (optional
	 * operation). Shifts any subsequent elements to the left (subtracts one
	 * from their indices). Returns the element that was removed from the
	 * list.
	 *
	 * @param index
	 *            the index of the element to be removed
	 * @return the element previously at the specified position
	 */
	public void remove(int index) {
		strings.remove(index);
		if (index >= 0) {
			fireIntervalRemoved(this, index, index);
		}
	}

}
