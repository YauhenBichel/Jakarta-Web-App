package com.holidaysystem.common;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 * Utils for dates and time
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
public final class DateUtils {
	public static final String BASE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final DateTimeFormatter FORMATTER = 
		    new DateTimeFormatterBuilder().appendPattern(BASE_PATTERN)
		        .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true).toFormatter();
}
