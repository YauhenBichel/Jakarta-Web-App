/*
 *     Copyright 2022-2022 Yauhen Bichel yb3129h@gre.ac.uk OR bichel.eugen@gmail.com 
 *     Student Id 001185491
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
