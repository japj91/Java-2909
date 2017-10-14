package A00980851.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Frame extends JFrame {

	private JPanel contentPane;
	JMenuItem total;
	JMenuItem descripition;
	JMenuItem count;
	JMenuItem make;
	JMenu inventory;
	JCheckBoxMenuItem descending;
	JMenu file;
	JMenu data;
	JMenu help;
	JMenuItem about;
	GuiController guiController = new GuiController();

	/**
	 * Create the frame.
	 */
	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Assignment 2 -Comp 2613");
		createMenu();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel lblWelcomeToAssignment = new JLabel("Welcome to Assignment 2");
		lblWelcomeToAssignment.setBounds(new Rectangle(20, 0, 0, 0));
		contentPane.add(lblWelcomeToAssignment, BorderLayout.CENTER);
	}

	/**
	 * creates the menu and adds the action listeners
	 */
	public void createMenu() {
		JMenuBar menu = new JMenuBar();

		file = new JMenu("File");
		data = new JMenu("Data");
		help = new JMenu("Help");

		about = new JMenuItem("About");
		about.addActionListener(e -> {
			JOptionPane.showMessageDialog(Frame.this,
					"Comp 2613 Assignemnt two\n "
							+ "UI development, pulling form Derby DatabaseSetup information on Inventory",
					"Project Info", 1);
		});
		KeyStroke f1 = KeyStroke.getKeyStroke("F1");
		about.setAccelerator(f1);

		JMenuItem quit = new JMenuItem("Quit");
		quit.addActionListener(e -> {
			System.exit(0);
		});

		JMenuItem cusomters = new JMenuItem("Customers");
		cusomters.addActionListener(e -> {
			JOptionPane.showMessageDialog(Frame.this,
					"Comp 2613 Assignemnt two\n " + "Customers Feature has not yet been Implemented", "Project Info",
					1);
		});

		inventory = new JMenu("Inventory");

		JMenuItem reports = new JMenuItem("Reports");
		reports.addActionListener(e -> {
			JOptionPane.showMessageDialog(Frame.this,
					"Comp 2613 Assignemnt two\n" + "Reports feature has not yet been Implemented", "Project Info", 1);
		});

		total = new JMenuItem("Total");
		descripition = new JMenuItem("By Description");
		count = new JMenuItem("Count");
		make = new JMenuItem("Make");
		descending = new JCheckBoxMenuItem("Descending");

		setJMenuBar(menu);
		menu.add(file);
		menu.add(data);
		menu.add(help);

		file.add(quit);

		help.add(about);

		data.add(cusomters);
		data.add(inventory);
		data.add(reports);

		inventory.add(total);
		inventory.add(descending);
		inventory.add(count);
		inventory.add(make);
		inventory.add(descripition);

		total.addActionListener(e -> {
			totalSelected();
		});

		descripition.addActionListener(e -> {
			descriptionSelected();
		});

		count.addActionListener(e -> {
			countSelected(getDescendingStatus());
		});

		make.addActionListener(e -> {
			makeSelected(getDescendingStatus());
		});

	}

	private void makeSelected(boolean descendingStatus) {
		guiController.getMake(descendingStatus);
	}

	private void countSelected(boolean descendingStatus) {
		guiController.getCount(descendingStatus);
	}

	private void descriptionSelected() {
		guiController.getDesc(getDescendingStatus());
	}

	public void totalSelected() {
		Double total = guiController.getTotal();
		JOptionPane.showMessageDialog(Frame.this, String.format("Total for all inventory is %.2f", total),
				"Total of all inventory", JOptionPane.INFORMATION_MESSAGE);
	}

	private boolean getDescendingStatus() {
		return descending.getState();
	}

}
