package examples.core;

import java.text.DateFormat;
import java.util.Calendar;

public class Shape {

	private final Calendar date;

	public Shape() {
		date = Calendar.getInstance();
	}

	public String getDateCreated() {
		return DateFormat.getDateTimeInstance().format(date);
	}
}
