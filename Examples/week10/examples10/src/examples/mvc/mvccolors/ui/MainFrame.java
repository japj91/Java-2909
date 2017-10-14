package examples.mvc.mvccolors.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import examples.mvc.mvccolors.data.Colors;
import examples.mvc.mvccolors.data.NamedColor;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private ColorsModel colorsModel;
	private ColorsController colorsController;
	private JList<ColorItem> colorsView;
	private Swatch colorSwatch;
	private DescriptionField colorDescription;

	public MainFrame() {
		super("ColorsMVCTest");

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);

		create();
	}

	private void create() {
		// create the list
		colorsModel = new ColorsModel();
		colorsController = new ColorsController();
		colorsView = new JList<>(colorsModel);
		ListSelectionModel listSelectionModel = colorsView.getSelectionModel();
		listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		colorsView.addListSelectionListener(colorsController);
		JScrollPane colorsScrollPane = new JScrollPane(colorsView);
		colorsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		colorsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		colorsScrollPane.setPreferredSize(new Dimension(150, 20));
		add(colorsScrollPane, BorderLayout.WEST);

		// create the details view
		JPanel detailsView = new JPanel();
		add(detailsView, BorderLayout.CENTER);
		detailsView.setLayout(new GridLayout(0, 1));

		colorSwatch = new Swatch();
		colorSwatch.setOpaque(true);
		detailsView.add(colorSwatch);

		colorDescription = new DescriptionField();
		detailsView.add(colorDescription);

		new ConsoleLogger();
	}

	// Inner classes ---------------------------------------------------------

	private class ColorsController implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent event) {
			if (event.getValueIsAdjusting()) {
				return;
			}

			Object o = colorsView.getSelectedValue();
			if (o == null) {
				return;
			}

			ColorItem item = (ColorItem) o;
			NamedColor namedColor = item.getColor();
			Colors.getTheInstance().newColorPicked(namedColor);
		}

	}

}
