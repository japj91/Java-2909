/**
 * 
 */
package examples.mvc.mvccolors.ui;

import java.util.Observable;
import java.util.Observer;

import examples.mvc.mvccolors.data.Colors;
import examples.mvc.mvccolors.data.NamedColor;

/**
 * @author scirka
 *
 */
public class ConsoleLogger implements Observer {
	
	public ConsoleLogger() {
		Colors.getTheInstance().addObserver(this);
	}

	@Override
	public void update(Observable observable, Object o) {
		if (!(o instanceof NamedColor)) {
			return;
		}
		
		NamedColor namedColor = (NamedColor) o;
		System.out.println(namedColor);
	}
	
}
