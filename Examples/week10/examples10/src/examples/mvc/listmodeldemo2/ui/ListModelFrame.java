package examples.mvc.listmodeldemo2.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class ListModelFrame extends JFrame {

	private DemoListModel listModel;
	private JList<String> theList;
	private JButton addButton;
	private JButton removeButton;

	public ListModelFrame(DemoListModel listModel) {
		createUI(listModel);
	}

	private void createUI(DemoListModel listModel) {
		this.listModel = listModel;
		theList = new JList<>(listModel);
		addButton = new JButton("Add new item");
		removeButton = new JButton("Remove selected item");

		JPanel panel = new JPanel();
		panel.add(addButton);
		panel.add(removeButton);

		add(panel, BorderLayout.NORTH);
		add(new JScrollPane(theList), BorderLayout.CENTER);

		// Register listeners
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doAdd();
			}
		});

		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doRemove();
			}
		});

		setSize(300, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	protected void doRemove() {
		listModel.remove(theList.getSelectedIndex());
	}

	protected void doAdd() {
		String newItem = JOptionPane.showInputDialog("Enter a new item");

		if (newItem != null) {
			if (theList.getSelectedIndex() == -1) {
				listModel.add(newItem);
			} else {
				listModel.add(theList.getSelectedIndex() + 1, newItem);
			}
		}
	}
}
