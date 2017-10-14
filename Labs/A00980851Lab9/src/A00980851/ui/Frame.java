package A00980851.ui;

import A00980851.jdbc.dao.DbTest;
import A00980851.data.Customer;
import A00980851.io.ApplicationException;
import net.miginfocom.swing.MigLayout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class Frame extends JFrame {

	private JPanel contentPane;
	DbTest dbTest;
	private static final Logger LOG = LogManager.getLogger();


	/**
	 * Menu, Mnemonic, dialogs and Joption pants are created here
	 * Create the frame.
	 */
	public Frame(DbTest dbTest)throws SQLException,ApplicationException {
		setTitle("Lab 9 - UI Development");
		this.dbTest = dbTest;
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][pref!,grow,center][][][]"));
		createMenu();

		JLabel lblNewLabel_1 = new JLabel("");
		contentPane.add(lblNewLabel_1, "cell 1 1");
		
		JLabel lblNewLabel = new JLabel("Welcome to Lab 9");
		contentPane.add(lblNewLabel, "cell 1 5,alignx center");
	}

	/**
	 * Creation of the menu
	 * Mehtod is called from Frame Method
	 */
	private void createMenu() {
		JMenuBar jMenuBar = new JMenuBar();

		JMenu file = new JMenu("File");
		file.setMnemonic('F');

		JMenuItem customerOption = new JMenuItem("Customer");
		customerOption.setMnemonic('C');
		customerOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,Event.ALT_MASK));


		JMenuItem exit = new JMenuItem("Exit",KeyEvent.VK_F2);
		exit.setMnemonic('E');
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,Event.ALT_MASK));


		exit.addActionListener(e -> {
			LOG.info("Shuting Down GUI");
			System.out.println("exit");
			System.exit(0);
			try {
				dbTest.shutdown();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		JMenu help = new JMenu("Help");
		help.setMnemonic('H');

		JMenuItem about = new JMenuItem("About");
		about.setMnemonic('A');
		KeyStroke f2KeyStroke = KeyStroke.getKeyStroke("F2");
		about.setAccelerator(f2KeyStroke);
		about.addActionListener(e -> {
			LOG.info("About Selected");
			infoDialog();
		});

		file.add(customerOption);
		file.add(exit);

		help.add(about);

		jMenuBar.add(file);
		jMenuBar.add(help);

		setJMenuBar(jMenuBar);

		customerOption.addActionListener(e -> {
			try {
				Customer customer = dbTest.randomCustomer();
				CustomerDialog customerInformation = new CustomerDialog(customer);
				LOG.info("Dialog opened");
			}
			catch (Exception e1) {
				e1.getMessage();
				e1.printStackTrace();
			}
		});
	}

	/**
	 * Info dialog that is called from createMenu Mehtod
	 */
	private void infoDialog() {
		JOptionPane.showMessageDialog(Frame.this,"Lab 9\nby Japneet Johal A00980851","Programmer Information",JOptionPane.INFORMATION_MESSAGE);
	}

}
