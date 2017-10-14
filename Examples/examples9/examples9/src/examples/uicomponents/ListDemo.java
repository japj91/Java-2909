package examples.uicomponents;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import examples.uicomponents.util.ImageUtil;

@SuppressWarnings("serial")
public class ListDemo extends JFrame {
	final int NUMBER_OF_FLAGS = 9;

	// Declare an array of Strings for flag titles
	private final String[] flagTitles = { "Canada", "China", "Denmark", "France", "Germany", "India", "Norway",
			"United Kingdom", "United States of America" };

	// The list for selecting countries
	private final JList<String> jlst = new JList<String>(flagTitles);

	// Declare an ImageIcon array for the national flags of 9 countries
	private final ImageIcon[] flagImages = { new ImageIcon(ImageUtil.loadImage2(ComboBoxDemo.class, "images/ca.gif")), //
			new ImageIcon(ImageUtil.loadImage2(ComboBoxDemo.class, "images/china.gif")), //
			new ImageIcon(ImageUtil.loadImage2(ComboBoxDemo.class, "images/denmark.gif")), //
			new ImageIcon(ImageUtil.loadImage2(ComboBoxDemo.class, "images/fr.gif")), //
			new ImageIcon(ImageUtil.loadImage2(ComboBoxDemo.class, "images/germany.gif")), //
			new ImageIcon(ImageUtil.loadImage2(ComboBoxDemo.class, "images/india.gif")), //
			new ImageIcon(ImageUtil.loadImage2(ComboBoxDemo.class, "images/norway.gif")), //
			new ImageIcon(ImageUtil.loadImage2(ComboBoxDemo.class, "images/uk.gif")), //
			new ImageIcon(ImageUtil.loadImage2(ComboBoxDemo.class, "images/us.gif")) //
	};

	// Arrays of labels for displaying images
	private final JLabel[] jlblImageViewer = new JLabel[NUMBER_OF_FLAGS];

	public static void main(String[] args) {
		ListDemo frame = new ListDemo();
		frame.setSize(650, 500);
		frame.setTitle("ListDemo");
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public ListDemo() {
		// Create a panel to hold nine labels
		JPanel p = new JPanel(new GridLayout(3, 3, 5, 5));

		for (int i = 0; i < NUMBER_OF_FLAGS; i++) {
			p.add(jlblImageViewer[i] = new JLabel());
			jlblImageViewer[i].setHorizontalAlignment(SwingConstants.CENTER);
		}

		// Add p and the list to the frame
		add(p, BorderLayout.CENTER);
		add(new JScrollPane(jlst), BorderLayout.WEST);

		// Register listeners
		jlst.addListSelectionListener(new ListSelectionListener() {
			/** Handle list selection */
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Get selected indices
				int[] indices = jlst.getSelectedIndices();

				int i;
				// Set icons in the labels
				for (i = 0; i < indices.length; i++) {
					jlblImageViewer[i].setIcon(flagImages[indices[i]]);
				}

				// Remove icons from the rest of the labels
				for (; i < NUMBER_OF_FLAGS; i++) {
					jlblImageViewer[i].setIcon(null);
				}
			}
		});
	}
}
