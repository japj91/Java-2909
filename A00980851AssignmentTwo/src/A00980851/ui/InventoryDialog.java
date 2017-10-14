package A00980851.ui;

import A00980851.data.Inventory;
import A00980851.db.controllers.InventoryController;
import net.miginfocom.swing.MigLayout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

public class InventoryDialog extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField idField;
    private JTextField description;
    private JTextField partNumber;
    private JTextField postalField;
    private JTextField quantity;
    private JTextField price;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField joinDate;

    private static final Logger LOG = LogManager.getLogger();

    /**
     * Create the dialog.
     */
    public InventoryDialog(Inventory customer, InventoryController dbInterface) {
        setTitle("Assignment 2- Inventory Individual Entry Dialog");
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

            idField.setText(String.valueOf(customer.getMotorCyleID()));

            idField.setEditable(false);
            idField.setBackground(null);
        }
        {
            JLabel lblNewLabel = new JLabel("Desc");
            contentPanel.add(lblNewLabel, "cell 1 2,alignx right");
        }
        {
            description = new JTextField();
            description.setPreferredSize(new Dimension(8, 26));
            contentPanel.add(description, "cell 3 2,growx");

            description.setText(customer.getDescription());
        }
        {
            JLabel lblNewLabel_1 = new JLabel("Part Number");
            contentPanel.add(lblNewLabel_1, "cell 1 4,alignx right");
        }
        {
            partNumber = new JTextField();
            partNumber.setPreferredSize(new Dimension(8, 26));
            contentPanel.add(partNumber, "cell 3 4,growx");
            partNumber.setText(customer.getPartNumber());
        }
        {
            JLabel lblNewLabel_2 = new JLabel("Price");
            contentPanel.add(lblNewLabel_2, "cell 1 6,alignx right");
        }
        {
            price = new JTextField();
            price.setPreferredSize(new Dimension(8, 26));
            contentPanel.add(price, "cell 3 6,growx");
            price.setText(customer.getPrice());
        }
        {
            JLabel lblNewLabel_3 = new JLabel("Quantity");
            contentPanel.add(lblNewLabel_3, "cell 1 8,alignx right");
        }
        {
            quantity = new JTextField();
            quantity.setPreferredSize(new Dimension(8, 26));
            contentPanel.add(quantity, "cell 3 8,growx");
            quantity.setText(String.format("%s",customer.getQuantity()));
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
                    String id = idField.getText();
                    String desc = description.getText();
                    String partNum = partNumber.getText();
                    String value = price.getText();
                    String quan = quantity.getText();

                    try {
                        dbInterface.update(new Inventory(id,desc,partNum,value,quan));

                    } catch (SQLException e1) {
                        LOG.error(e1.getMessage());
                    }

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
