package A00980851.ui;

import A00980851.data.Customer;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CustomerDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField idField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField postalField;
	private JTextField cityField;
	private JTextField streetField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTextField joinDate;


	/**
	 * Create the dialog.
	 * Sizing of JLabels, and JTextFields are here
	 */
	public CustomerDialog(Customer customer) {
		setTitle("Lab 9 - Customer Dialog");
		setVisible(true);
		setBounds(120, 120, 600, 380);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][][][403.00,grow][][][]", "[][][][][][][][][][][][][][][][][][]"));
		{
			JLabel lblNewLabel_7 = new JLabel("ID");
			contentPanel.add(lblNewLabel_7, "cell 1 0,alignx right");
		}
		{
			idField = new JTextField();
			idField.setPreferredSize(new Dimension(8, 26));
			contentPanel.add(idField, "cell 3 0,growx");
			idField.setText(String.valueOf(customer.getIdentifier()));
			idField.setEditable(false);
			idField.setBackground(null);
		}
		{
			JLabel lblNewLabel = new JLabel("First Name");
			contentPanel.add(lblNewLabel, "cell 1 2,alignx right");
		}
		{
			firstNameField = new JTextField();
			firstNameField.setPreferredSize(new Dimension(8, 26));
			contentPanel.add(firstNameField, "cell 3 2,growx");
			firstNameField.setText(customer.getFirstName());
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Last Name");
			contentPanel.add(lblNewLabel_1, "cell 1 4,alignx right");
		}
		{
			lastNameField = new JTextField();
			lastNameField.setPreferredSize(new Dimension(8, 26));
			contentPanel.add(lastNameField, "cell 3 4,growx");
			lastNameField.setText(customer.getLastName());
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Street");
			contentPanel.add(lblNewLabel_2, "cell 1 6,alignx right");
		}
		{
			streetField = new JTextField();
			streetField.setPreferredSize(new Dimension(8, 26));
			contentPanel.add(streetField, "cell 3 6,growx");
			streetField.setText(customer.getStreetName());
		}
		{
			JLabel lblNewLabel_3 = new JLabel("City");
			contentPanel.add(lblNewLabel_3, "cell 1 8,alignx right");
		}
		{
			cityField = new JTextField();
			cityField.setPreferredSize(new Dimension(8, 26));
			contentPanel.add(cityField, "cell 3 8,growx");
			cityField.setText(customer.getCity());
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Postal Code");
			contentPanel.add(lblNewLabel_4, "cell 1 11,alignx right");
		}
		{
			postalField = new JTextField();
			postalField.setPreferredSize(new Dimension(8, 26));
			contentPanel.add(postalField, "cell 3 11,growx");
			postalField.setText(customer.getPostalCode());
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Phone");
			contentPanel.add(lblNewLabel_5, "cell 1 13,alignx right");
		}
		{
			phoneField = new JTextField();
			phoneField.setPreferredSize(new Dimension(8, 26));
			contentPanel.add(phoneField, "cell 3 13,growx");
			phoneField.setText(customer.getPhoneNumber());
		}
		{
			JLabel lblNewLabel_6 = new JLabel("Email");
			contentPanel.add(lblNewLabel_6, "cell 1 15,alignx right");
		}
		{
			emailField = new JTextField();
			emailField.setPreferredSize(new Dimension(8, 26));
			contentPanel.add(emailField, "cell 3 15,growx");
			emailField.setText(customer.getEmailAddress());

			
		}
		{
			JLabel lblNewLabel_8 = new JLabel("Join Date");
			contentPanel.add(lblNewLabel_8, "cell 1 17,alignx right");
			
		}
		{
			joinDate = new JTextField();
			joinDate.setPreferredSize(new Dimension(8, 26));
			contentPanel.add(joinDate, "cell 3 17,growx");
			joinDate.setText(String.valueOf(customer.getDate()));

		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(e -> {
					dispose();
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(e ->{
					dispose();
				});
			}
		}
	}

}
