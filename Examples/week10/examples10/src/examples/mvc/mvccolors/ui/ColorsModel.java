/**
 * 
 */
package examples.mvc.mvccolors.ui;

import java.util.Iterator;

import javax.swing.DefaultListModel;

import examples.mvc.mvccolors.data.Colors;
import examples.mvc.mvccolors.data.NamedColor;

/**
 * @author scirka
 *
 */
@SuppressWarnings("serial")
public class ColorsModel extends DefaultListModel<ColorItem> {

	ColorsModel() {
		Iterator<NamedColor> iterator = Colors.getTheInstance().getIterator();
		while (iterator.hasNext()) {
			NamedColor color = iterator.next();
			// call DefaultListModel addElement(...)
			addElement(new ColorItem(color));
		}
	}
}