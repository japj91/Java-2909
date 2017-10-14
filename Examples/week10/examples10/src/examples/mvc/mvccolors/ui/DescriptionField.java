/**
 * 
 */
package examples.mvc.mvccolors.ui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import examples.mvc.mvccolors.data.Colors;
import examples.mvc.mvccolors.data.NamedColor;

/**
 * @author scirka
 *
 */
@SuppressWarnings("serial")
public class DescriptionField extends JLabel implements Observer {
	
	public DescriptionField() {
		Colors.getTheInstance().addObserver(this);
	}

	@Override
	public void update(Observable observable, Object o) {
		if (!(o instanceof NamedColor)) {
			return;
		}
		
		NamedColor namedColor = (NamedColor) o;
		setText(namedColor.getName() + " = " + namedColor);
	}
	
}

