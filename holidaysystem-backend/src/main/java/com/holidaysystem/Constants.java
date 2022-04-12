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
}
