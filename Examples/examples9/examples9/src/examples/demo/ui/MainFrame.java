/**
 * Project: examples9
 * File: MainFrame.java
 * Date: Nov 17, 2016
 * Time: 10:30:55 AM
 */

package examples.demo.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import examples.demo.data.Customer;
import net.miginfocom.swing.MigLayout;

/**
 * @author Sam Cirka, A00123456
 *
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField emailField;
	private JTextArea notesField;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("SwingDemo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][][grow]"));

		JLabel lblId = new JLabel("ID");
		contentPane.add(lblId, "cell 0 0,alignx trailing");

		idField = new JTextField();
		idField.setEnabled(false);
		idField.setEditable(false);
		contentPane.add(idField, "cell 1 0,growx");
		idField.setColumns(10);

		JLabel lblFirstName = new JLabel("First Name");
		contentPane.add(lblFirstName, "cell 0 1,alignx trailing");

		firstNameField = new JTextField();
		contentPane.add(firstNameField, "cell 1 1,growx");
		firstNameField.setColumns(10);

		JLabel lblLastName = new JLabel("Last Name");
		contentPane.add(lblLastName, "cell 0 2,alignx trailing");

		lastNameField = new JTextField();
		contentPane.add(lastNameField, "cell 1 2,growx");
		lastNameField.setColumns(10);

		JLabel lblEmail = new JLabel("Email");
		contentPane.add(lblEmail, "cell 0 3,alignx trailing");

		emailField = new JTextField();
		contentPane.add(emailField, "cell 1 3,growx");
		emailField.setColumns(10);

		JLabel lblNotes = new JLabel("Notes");
		contentPane.add(lblNotes, "cell 0 4");

		notesField = new JTextArea();
		notesField.setWrapStyleWord(true);
		notesField.setLineWrap(true);
		contentPane.add(new JScrollPane(notesField), "cell 1 4,grow");

		setData();
	}

	/**
	 * 
	 */
	private void setData() {
		Customer customer = getCustomer();
		idField.setText(Long.toString(customer.getId()));
		firstNameField.setText(customer.getFirstName());
		lastNameField.setText(customer.getLastName());
		emailField.setText(customer.getEmailAddress());

		notesField.setText(
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer a velit ac purus ornare dignissim. Aenean suscipit aliquet aliquam. Quisque eu mauris ut elit hendrerit condimentum in vestibulum ipsum. Pellentesque non ex quis sapien sollicitudin eleifend. Donec porta finibus volutpat. Proin ultrices odio quis pretium sodales. Fusce viverra nulla et aliquam posuere. Cras ac venenatis tortor. Nulla felis orci, ullamcorper a enim at, porttitor feugiat eros. Donec varius convallis augue. Integer eleifend, augue eu accumsan volutpat, ligula mauris vehicula urna, ut semper arcu odio ut diam.");

	}

	/**
	 * We should get this from the database, but this is only a demo, so keep it simple
	 * 
	 * @return a customer
	 */
	private Customer getCustomer() {
		return new Customer.Builder(427, "1-800-555-1212").setFirstName("Fred").setLastName("Fish").setEmailAddress("fred@fish.edu").build();
	}

}
