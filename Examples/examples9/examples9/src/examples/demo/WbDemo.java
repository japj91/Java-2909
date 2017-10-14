/**
 * Project: examples9
 * File: WbDemo.java
 * Date: Nov 17, 2016
 * Time: 10:30:23 AM
 */

package examples.demo;

import java.awt.EventQueue;

import examples.demo.ui.MainFrame;

/**
 * @author Sam Cirka, A00123456
 *
 */
public class WbDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
