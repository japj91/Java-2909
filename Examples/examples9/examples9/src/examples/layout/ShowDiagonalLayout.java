package examples.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class ShowDiagonalLayout extends JApplet {
	private final FlowLayout flowLayout = new FlowLayout();
	private final GridLayout gridLayout = new GridLayout(2, 2);
	private final DiagonalLayout diagonalLayout = new DiagonalLayout();

	private final JButton jbt1 = new JButton("Button 1");
	private final JButton jbt2 = new JButton("Button 2");
	private final JButton jbt3 = new JButton("Button 3");
	private final JButton jbt4 = new JButton("Button 4");

	private final JRadioButton jrbFlowLayout = new JRadioButton("FlowLayout");
	private final JRadioButton jrbGridLayout = new JRadioButton("GridLayout");
	private final JRadioButton jrbDiagonalLayout = new JRadioButton("DiagonalLayout", true);

	private final JPanel jPanel2 = new JPanel();

	public ShowDiagonalLayout() {
		// Set default layout in jPanel2
		jPanel2.setLayout(diagonalLayout);
		jPanel2.add(jbt1);
		jPanel2.add(jbt2);
		jPanel2.add(jbt3);
		jPanel2.add(jbt4);
		jPanel2.setBorder(new LineBorder(Color.black));

		JPanel jPanel1 = new JPanel();
		jPanel1.setBorder(new TitledBorder("Select a Layout Manager"));
		jPanel1.add(jrbFlowLayout);
		jPanel1.add(jrbGridLayout);
		jPanel1.add(jrbDiagonalLayout);

		ButtonGroup buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(jrbFlowLayout);
		buttonGroup1.add(jrbGridLayout);
		buttonGroup1.add(jrbDiagonalLayout);

		add(jPanel1, BorderLayout.SOUTH);
		add(jPanel2, BorderLayout.CENTER);

		jrbFlowLayout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jPanel2.setLayout(flowLayout);
				jPanel2.validate();
			}
		});
		jrbGridLayout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jPanel2.setLayout(gridLayout);
				jPanel2.validate();
			}
		});
		jrbDiagonalLayout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jPanel2.setLayout(diagonalLayout);
				jPanel2.validate();
			}
		});
	}

	public static void main(String[] args) {
		ShowDiagonalLayout applet = new ShowDiagonalLayout();
		JFrame frame = new JFrame();
		// EXIT_ON_CLOSE == 3
		frame.setDefaultCloseOperation(3);
		frame.setTitle("ShowDiagonalLayout");
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(400, 320);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);
		frame.setVisible(true);
	}
}
