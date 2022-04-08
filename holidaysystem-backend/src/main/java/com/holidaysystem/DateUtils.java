package com.holidaysystem;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 * 
 * @author yauhen bichel
 *
 */
public final class DateUtils {
	public static final String BASE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final DateTimeFormatter FORMATTER = 
		    new DateTimeFormatterBuilder().appendPattern(BASE_PATTERN)
		        .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true).toFormatter();
}
