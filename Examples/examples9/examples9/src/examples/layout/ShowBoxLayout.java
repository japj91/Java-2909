package examples.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ShowBoxLayout {

	private static JFrame frame;

	// Create two box containers
	private final Box box1 = Box.createHorizontalBox();
	private final Box box2 = Box.createVerticalBox();

	// Create a label to display flags
	private final JLabel jlblFlag = new JLabel();

	// Create image icons for flags
	private final ImageIcon imageIconUS = new ImageIcon(getClass().getResource("../uicomponents/images/us.gif"));
	private final ImageIcon imageIconCanada = new ImageIcon(getClass().getResource("../uicomponents/images/ca.gif"));
	private final ImageIcon imageIconNorway = new ImageIcon(getClass().getResource("../uicomponents/images/norway.gif"));
	private final ImageIcon imageIconGermany = new ImageIcon(getClass().getResource("../uicomponents/images/germany.gif"));
	private final ImageIcon imageIconPrint = new ImageIcon(getClass().getResource("../uicomponents/images/print.gif"));
	private final ImageIcon imageIconSave = new ImageIcon(getClass().getResource("../uicomponents/images/save.gif"));

	// Create buttons to select images
	private final JButton jbtUS = new JButton("US");
	private final JButton jbtCanada = new JButton("Canada");
	private final JButton jbtNorway = new JButton("Norway");
	private final JButton jbtGermany = new JButton("Germany");

	private void createUi() {
		box1.add(new JButton(imageIconPrint));
		box1.add(Box.createHorizontalStrut(20));
		box1.add(new JButton(imageIconSave));

		box2.add(jbtUS);
		box2.add(Box.createVerticalStrut(8));
		box2.add(jbtCanada);
		box2.add(Box.createGlue());
		box2.add(jbtNorway);
		box2.add(Box.createRigidArea(new Dimension(10, 8)));
		box2.add(jbtGermany);

		box1.setBorder(new javax.swing.border.LineBorder(Color.red));
		box2.setBorder(new javax.swing.border.LineBorder(Color.black));

		Container contentPane = frame.getContentPane();
		contentPane.add(box1, BorderLayout.NORTH);
		contentPane.add(box2, BorderLayout.EAST);
		contentPane.add(jlblFlag, BorderLayout.CENTER);

		// Register listeners
		jbtUS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jlblFlag.setIcon(imageIconUS);
			}
		});
		jbtCanada.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jlblFlag.setIcon(imageIconCanada);
			}
		});
		jbtNorway.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jlblFlag.setIcon(imageIconNorway);
			}
		});
		jbtGermany.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jlblFlag.setIcon(imageIconGermany);
			}
		});
	}

	public static void main(String[] args) {
		ShowBoxLayout app = new ShowBoxLayout();
		frame = new JFrame();
		app.createUi();
		// EXIT_ON_CLOSE == 3
		frame.setDefaultCloseOperation(3);
		frame.setTitle("ShowBoxLayout");
		frame.setSize(400, 320);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);
		frame.setVisible(true);
	}
}
