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
package com.holidaysystem;

/**
 * Constants
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
public final class Constants {
	public static final String JMS_PREFIX = "java:/jms/";
	public static final String MESSAGE_QUEUE = "HolidayRequestQueue";
	public static final String JMS_MESSAGE_QUEUE = JMS_PREFIX + MESSAGE_QUEUE;
	public static final String HOLIDAY_REQUEST_TOPIC = "HolidayRequestTopic";
	public static final String JMS_HOLIDAY_REQUEST_TOPIC = JMS_PREFIX + HOLIDAY_REQUEST_TOPIC;
	public static final String JNDI_CONNECTION_FACTORY = "java:/ConnectionFactory";
	
	public static final String QUEUE_KEY_MESSAGE = "json-request";
	
	public static final String DATASOURCE_LOOKUP_KEY = "java:/PostgresDS";
	
	public static final String UNCAUGHT_EXCEPTION_MESSAGE = "We are sorry. We are working to fix the issue. Please try later again.";
	
	public static final Integer PERCENT_MEMBERS_ON_DUTY = 60;
	public static final Integer PERCENT_MEMBERS_AUGUST_ON_DUTY = 40;
}
