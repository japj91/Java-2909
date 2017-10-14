package examples.core;

import java.text.DateFormat;
import java.util.Date;

public class Shape {

	private final Date date;

	public Shape() {
		date = new Date();
	}

	public String getDateCreated() {
		return DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(date);
	}
}
