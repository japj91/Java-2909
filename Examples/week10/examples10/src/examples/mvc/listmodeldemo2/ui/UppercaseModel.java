package examples.mvc.listmodeldemo2.ui;

@SuppressWarnings("serial")
public class UppercaseModel extends DemoListModel {

	public UppercaseModel() {
	}

	/**
	 * Add an element to the list. Modify the behaviour to change the text to uppercase
	 */
	@Override
	public void add(String value) {
		super.add(value.toUpperCase());
	}

	@Override
	public void add(int index, String value) {
		super.add(index, value.toUpperCase());
	}

}
