package A00980851.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InventoryTable extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JTable table;

	/**
	 * Create the dialog.
	 */
	public void run(JTable jTable) {
		this.table = jTable;

		setVisible(true);
		setBounds(100, 100, 950, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

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
				cancelButton.addActionListener(e -> {
					dispose();
				});
			}
		}
		addTable();
	}

	/**
	 * adds tables
	 */
	private void addTable() {

		table.setPreferredScrollableViewportSize(new Dimension(800, 200));
		table.setFillsViewportHeight(true);

		JScrollPane jScrollPane = new JScrollPane(table);
		add(jScrollPane);
	}

}
