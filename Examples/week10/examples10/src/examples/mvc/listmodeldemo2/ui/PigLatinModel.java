package examples.mvc.listmodeldemo2.ui;

@SuppressWarnings("serial")
public class PigLatinModel extends DemoListModel {

	public PigLatinModel() {
	}

	/**
	 * Add an element to the list. Modify the behaviour to change the text to 'pig-latin'
	 */
	@Override
	public void add(int index, String value) {
		char c = value.charAt(0);
		value = String.format("%s-%say", value.substring(1), c);
		super.add(index, value);
	}

}
