package examples.core.dateandtime;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

class DateAndTimeTest {
	public static void main(String[] args) {
		GregorianCalendar birthDate = new GregorianCalendar(1982, Calendar.MAY, 1, 7, 55);
		System.out.println("1. " + birthDate);

		Date date = birthDate.getTime();
		System.out.println("\n2. " + date);

		DateFormat stdFormat = DateFormat.getInstance();
		String stdString = stdFormat.format(date);
		System.out.println("\n3. " + stdString);
		DateFormat dateFormat = DateFormat.getDateInstance();
		String dateString = dateFormat.format(date);
		System.out.println("\n4. " + dateString);
		DateFormat timeFormat = DateFormat.getTimeInstance();
		String timeString = timeFormat.format(date);
		System.out.println("\n5. " + timeString);
		DateFormat dateAndTimeFormat1 = DateFormat.getDateTimeInstance();

		DateFormat dateFormat1 = DateFormat.getDateInstance();
		String dateAndTimeString1 = dateAndTimeFormat1.format(date);
		System.out.println("\n6. " + dateAndTimeString1);
		DateFormat dateAndTimeFormat2 = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.LONG);
		String dateAndTimeString2 = dateAndTimeFormat2.format(date);
		System.out.println("\n7. " + dateAndTimeString2);
	}
}
