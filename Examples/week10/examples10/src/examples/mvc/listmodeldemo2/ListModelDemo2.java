package examples.mvc.listmodeldemo2;

import javax.swing.UIManager;

import examples.mvc.listmodeldemo2.ui.DemoListModel;
import examples.mvc.listmodeldemo2.ui.ListModelFrame;
import examples.mvc.listmodeldemo2.ui.PigLatinModel;

public class ListModelDemo2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			// ignore it
		}

		// DemoListModel listModel = new UppercaseModel();
		DemoListModel listModel = new PigLatinModel();
		ListModelFrame frame = new ListModelFrame(listModel);
		frame.setVisible(true);
	}

}
