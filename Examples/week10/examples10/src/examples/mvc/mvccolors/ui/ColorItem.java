/**
 * 
 */
package examples.mvc.mvccolors.ui;

import examples.mvc.mvccolors.data.NamedColor;

/**
 * @author scirka
 *
 */
public class ColorItem {

	private final NamedColor color;

	ColorItem(NamedColor color) {
		this.color = color;
	}

	/**
	 * @return Returns the color.
	 */
	public NamedColor getColor() {
		return color;
	}

	@Override
	public String toString() {
		return color.getName();
	}
}

