package examples.core.dateandtime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateParsingAndFormatting {

	public static void main(String[] args) {
		String dateString = "2015-08-04";
		LocalDate localDate = LocalDate.parse(dateString);
		System.out.println("Date: " + localDate);
		System.out.println("Year: " + localDate.getYear());
		System.out.println("Month: " + localDate.getMonth());
		System.out.println("Day: " + localDate.getDayOfMonth());

		String dateWithTimeString = "2015-08-04T10:11:30";
		LocalDateTime localDateTime = LocalDateTime.parse(dateWithTimeString);
		System.out.println("Date with Time: " + localDateTime);
		System.out.println("Hour: " + localDateTime.getHour());
		System.out.println("Minute: " + localDateTime.getMinute());
		System.out.println("Second: " + localDateTime.getSecond());

		DateTimeFormatter dateTimeForatter = DateTimeFormatter.ofPattern("dd MMM uuuu");
		String anotherDateString = "04 Aug 2015";
		LocalDate localDate2 = LocalDate.parse(anotherDateString, dateTimeForatter);
		System.out.println(anotherDateString + " parses to " + localDate2);

		DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
		LocalDate localDate3 = LocalDate.of(2015, 8, 4);
		System.out.println(localDate3 + " is " + DATE_FORMAT.format(localDate3));

		// Custom format
		LocalDate beginningOfTheEnd = LocalDate.of(2017, Month.JANUARY, 20);
		System.out.println("Formatting " + beginningOfTheEnd + " with pattern \"MM/dd/yyyy\": " + format(beginningOfTheEnd, "MM/dd/yyyy"));

		ZonedDateTime zonedDateTime = ZonedDateTime.parse("03-02-2017 13:52:00 PDT", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss zzz"));
		System.out.println(zonedDateTime);
	}

	private static String format(LocalDate date, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return formatter.format(date);
	}
}
