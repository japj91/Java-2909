package examples.uicomponents.joptionpane;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import examples.uicomponents.ButtonDemo;
import examples.uicomponents.util.ImageUtil;

/**
 * A frame that contains settings for selecting various option dialogs.
 */
@SuppressWarnings("serial")
class OptionDialogFrame extends JFrame {

	public static final int DEFAULT_WIDTH = 600;
	public static final int DEFAULT_HEIGHT = 400;
	private final ButtonPanel typePanel;
	private final ButtonPanel messagePanel;
	private final ButtonPanel messageTypePanel;
	private final ButtonPanel optionTypePanel;
	private final ButtonPanel optionsPanel;
	private final ButtonPanel inputPanel;
	private final String messageString = "Message";
	private final Icon messageIcon;
	private final Object messageObject;
	private final Component messageComponent;

	public OptionDialogFrame() {
		setTitle("OptionDialogTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		messageIcon = new ImageIcon(ImageUtil.loadImage2(ButtonDemo.class, "images/blue-ball.gif"));
		messageObject = new Date();
		messageComponent = new SampleComponent();

		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(2, 3));

		typePanel = new ButtonPanel("Type", "Message", "Confirm", "Option", "Input");
		messageTypePanel = new ButtonPanel("Message Type", "ERROR_MESSAGE", "INFORMATION_MESSAGE", "WARNING_MESSAGE",
				"QUESTION_MESSAGE", "PLAIN_MESSAGE");
		messagePanel = new ButtonPanel("Message", "String", "Icon", "Component", "Other", "Object[]");
		optionTypePanel = new ButtonPanel("Confirm", "DEFAULT_OPTION", "YES_NO_OPTION", "YES_NO_CANCEL_OPTION",
				"OK_CANCEL_OPTION");
		optionsPanel = new ButtonPanel("Option", "String[]", "Icon[]", "Object[]");
		inputPanel = new ButtonPanel("Input", "Text field", "Combo box");

		gridPanel.add(typePanel);
		gridPanel.add(messageTypePanel);
		gridPanel.add(messagePanel);
		gridPanel.add(optionTypePanel);
		gridPanel.add(optionsPanel);
		gridPanel.add(inputPanel);

		// add a panel with a Show button

		JPanel showPanel = new JPanel();
		JButton showButton = new JButton("Show");
		showButton.addActionListener(new ShowAction());
		showPanel.add(showButton);

		add(gridPanel, BorderLayout.CENTER);
		add(showPanel, BorderLayout.SOUTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Gets the currently selected message.
	 * 
	 * @return a string, icon, component or object array, depending on the Message panel selection
	 */
	public Object getMessage() {
		String s = messagePanel.getSelection();
		if (s.equals("String")) {
			return messageString;
		} else if (s.equals("Icon")) {
			return messageIcon;
		} else if (s.equals("Component")) {
			return messageComponent;
		} else if (s.equals("Object[]")) {
			return new Object[] { messageString, messageIcon, messageComponent, messageObject };
		} else if (s.equals("Other")) {
			return messageObject;
		} else {
			return null;
		}
	}

	/**
	 * Gets the currently selected options.
	 * 
	 * @return an array of strings, icons or objects, depending on the Option panel selection
	 */
	public Object[] getOptions() {
		String s = optionsPanel.getSelection();
		if (s.equals("String[]")) {
			return new String[] { "Yellow", "Blue", "Red" };
		} else if (s.equals("Icon[]")) {
			return new Icon[] { new ImageIcon("yellow-ball.gif"), new ImageIcon("blue-ball.gif"),
					new ImageIcon("red-ball.gif") };
		} else if (s.equals("Object[]")) {
			return new Object[] { messageString, messageIcon, messageComponent, messageObject };
		} else {
			return null;
		}
	}

	/**
	 * Gets the selected message or option type
	 * 
	 * @param panel
	 *            the Message Type or Confirm panel
	 * @return the selected XXX_MESSAGE or XXX_OPTION constant from the JOptionPane class
	 */
	public int getType(ButtonPanel panel) {
		String s = panel.getSelection();
		try {
			return JOptionPane.class.getField(s).getInt(null);
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * The action listener for the Show button shows a Confirm, Input, Message or Option dialog depending on the Type
	 * panel selection.
	 */
	private class ShowAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (typePanel.getSelection().equals("Confirm")) {
				JOptionPane.showConfirmDialog(OptionDialogFrame.this, getMessage(), "Title", getType(optionTypePanel),
						getType(messageTypePanel));
			} else if (typePanel.getSelection().equals("Input")) {
				if (inputPanel.getSelection().equals("Text field")) {
					JOptionPane.showInputDialog(OptionDialogFrame.this, getMessage(), "Title",
							getType(messageTypePanel));
				} else {
					JOptionPane.showInputDialog(OptionDialogFrame.this, getMessage(), "Title",
							getType(messageTypePanel), null, new String[] { "Yellow", "Blue", "Red" }, "Blue");
				}
			} else if (typePanel.getSelection().equals("Message")) {
				JOptionPane.showMessageDialog(OptionDialogFrame.this, getMessage(), "Title", getType(messageTypePanel));
			} else if (typePanel.getSelection().equals("Option")) {
				JOptionPane.showOptionDialog(OptionDialogFrame.this, getMessage(), "Title", getType(optionTypePanel),
						getType(messageTypePanel), null, getOptions(), getOptions()[0]);
			}
		}
	}

}