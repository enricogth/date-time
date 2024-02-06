package aulas;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class dateTime {

	public static void main(String[] args) {
		//https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/time/format/DateTimeFormatter.html
		//DateTimeFormatt is a custom form, you can type 20/07/2022 and the program will read as 2022-07-20
		DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		DateTimeFormatter fmt3 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault()); // use the computer local time as time zone
		DateTimeFormatter fmt4 = DateTimeFormatter.ISO_DATE_TIME; // is a custom DateTime in ISO 8601 format(text), Date and Time with ZoneId 
		DateTimeFormatter fmt5 = DateTimeFormatter.ISO_INSTANT; // again ISO 8601 format, but to instant DateTime
		
		
		LocalDate d01 = LocalDate.now();
		LocalDateTime d02 = LocalDateTime.now();
		
		
		//parse = put DateTime in format of ISO 8601(text). Is a static value, it doesn't change
		Instant d03 = Instant.now();
		LocalDate d04 = LocalDate.parse("2022-07-20");
		LocalDateTime d05 = LocalDateTime.parse("2022-07-20T01:30:26");
		Instant d06 = Instant.parse("2022-07-20T01:30:26Z");
		Instant d07 = Instant.parse("2022-07-20T01:30:26-03:00");
		
		
		LocalDate d08 = LocalDate.parse("20/07/2022", fmt1);
		LocalDateTime d09 = LocalDateTime.parse("20/07/2022 01:30", fmt2);
		
		
		// transform year, month, day ---> year-month-day (can add time too, hour, minute, second, but need LocalDateTime
		LocalDate d10 = LocalDate.of(2022, 07, 20);
		LocalDateTime d11 = LocalDateTime.of(2022, 07, 20, 1, 30);
		
		System.out.println("d01 = " + d01);
		System.out.println("d02 = " + d02);
		System.out.println("d03 = " + d03);
		System.out.println("d04 = " + d04);
		System.out.println("d05 = " + d05);
		System.out.println("d06 = " + d06);
		System.out.println("d07 = " + d07);
		System.out.println("d08 = " + d08);
		System.out.println("d09 = " + d09);
		System.out.println("d10 = " + d10);
		System.out.println("d11 = " + d11);
		System.out.println();
		
		
		// transform DateTime in custom format, example: (00/00/0000)
		System.out.println("d04 = " + d04.format(fmt1));
		System.out.println("d04 = " + fmt1.format(d04));
		System.out.println("d04 = " + d04.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
		
		System.out.println();
		System.out.println("d05 = " + d05.format(fmt1));
		System.out.println("d05 = " + d05.format(fmt2)); //fmt2 can show the time too
		System.out.println("d05 = " + d05.format(fmt4));
		
		System.out.println();
		System.out.println("d06 = " + fmt3.format(d06)); //fmt3 will took your computer local time (-03:00), and compare with zulu time (00:00)
		System.out.println("d06 = " + fmt5.format(d06));
		System.out.println();
		
		
		LocalDate r1 = LocalDate.ofInstant(d06, ZoneId.systemDefault()); //convert LocalDate to local system time(Brazil -03:00)
		LocalDate r2 = LocalDate.ofInstant(d06, ZoneId.of("Portugal"));
		LocalDateTime r3 = LocalDateTime.ofInstant(d06, ZoneId.systemDefault());
		LocalDateTime r4 = LocalDateTime.ofInstant(d06, ZoneId.of("Portugal")); // convert LocalDateTime to Portugal zone time +01:00
		
		System.out.println("r1 = " + r1);
		System.out.println("r2 = " + r2);
		System.out.println("r3 = " + r3);
		System.out.println("r4 = " + r4);
		System.out.println();
		
		
		// get the day, month or year of the date
		System.out.println("d04 dia = " + d04.getDayOfMonth());
		System.out.println("d04 mês = " + d04.getMonthValue());
		System.out.println("d04 ano = " + d04.getYear());
		System.out.println();
		
		
		// add  or subtract how many days,hours... do you want, based on d04 date
		LocalDate pastWeekLocalDate = d04.minusDays(7); // 7 days = 1 week
		LocalDate nextWeekLocalDate = d04.plusDays(7);
		
		System.out.println("pastWeekLocalDate = " + pastWeekLocalDate);
		System.out.println("nextWeekLocalDate = " + nextWeekLocalDate);
		
		LocalDateTime pastWeekLocalDateTime = d05.minusDays(7);
		LocalDateTime nextWeekLocalDateTime = d05.plusDays(7);
		
		System.out.println("pastWeekLocalDateTime = " + pastWeekLocalDateTime);
		System.out.println("nextWeekLocalDateTime = " + nextWeekLocalDateTime);
		
		
		// with instant now
		Instant pastWeekInstant = d06.minus(7, ChronoUnit.DAYS); //with Instant, only ChronoUnit accept Days,Months, etc...
		Instant nextWeekInstant = d06.plus(7, ChronoUnit.DAYS);
		
		System.out.println("pasWeekInstant = " + pastWeekInstant);
		System.out.println("nextWeekInstant = " + nextWeekInstant);
		System.out.println();
		
		
		// how to calculate the duration between 2 dates, but only dates with time 
		Duration t1 = Duration.between(pastWeekLocalDateTime , d05);
		Duration t2 = Duration.between(pastWeekLocalDate.atTime(0, 0), d04.atTime(0, 0)); // atTime convert LocalDate to LocalDateTime!!!
		Duration t3 = Duration.between(pastWeekLocalDate.atStartOfDay(), d04.atStartOfDay()); // atStartofDay = 00:00 midnight, convert to LocalDateTime too
		Duration t4 = Duration.between(pastWeekInstant, d06);
		Duration t5 = Duration.between(d06, pastWeekInstant);
		
		
		System.out.println("t1 days = " + t1.toDays()); //duration between Days
		System.out.println("t2 days = " + t2.toDays());
		System.out.println("t3 days = " + t3.toDays());
		System.out.println("t4 days = " + t4.toDays());
		System.out.println("t5 days = " + t5.toDays());
		
	}

}
